import org.gradle.api.file.RegularFileProperty
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import java.nio.channels.FileChannel
import java.nio.channels.FileLock
import java.nio.channels.OverlappingFileLockException
import java.nio.file.StandardOpenOption.CREATE
import java.nio.file.StandardOpenOption.WRITE

/**
 * Coordinates CocoaPods `xcodebuild` runs. They share one synthetic Pods.xcodeproj
 * and Xcode's build database (`build.db`) must not be opened by parallel builds,
 * even when Android Studio and Xcode launch separate Gradle invocations.
 */
abstract class CocoaPodsXcodeBuildService :
    BuildService<CocoaPodsXcodeBuildService.Parameters>,
    AutoCloseable {

    interface Parameters : BuildServiceParameters {
        val lockFile: RegularFileProperty
    }

    private data class HeldLock(
        val channel: FileChannel,
        val lock: FileLock,
    )

    private var ownerTaskPath: String? = null
    private var heldLock: HeldLock? = null

    @Synchronized
    fun acquire(taskPath: String) {
        heldLock?.let {
            ownerTaskPath = taskPath
            return
        }

        val lockFile = parameters.lockFile.get().asFile
        lockFile.parentFile.mkdirs()

        val channel = FileChannel.open(lockFile.toPath(), CREATE, WRITE)
        try {
            val lock = waitForLock(channel)
            ownerTaskPath = taskPath
            heldLock = HeldLock(channel, lock)
        } catch (failure: Throwable) {
            channel.close()
            throw failure
        }
    }

    @Synchronized
    fun release(taskPath: String) {
        if (ownerTaskPath != taskPath) return
        ownerTaskPath = null
        heldLock?.close()
        heldLock = null
    }

    @Synchronized
    override fun close() {
        ownerTaskPath = null
        heldLock?.close()
        heldLock = null
    }

    private fun waitForLock(channel: FileChannel): FileLock {
        while (true) {
            try {
                return channel.lock()
            } catch (_: OverlappingFileLockException) {
                sleepBeforeRetry()
            }
        }
    }

    private fun sleepBeforeRetry() {
        try {
            Thread.sleep(250)
        } catch (interrupted: InterruptedException) {
            Thread.currentThread().interrupt()
            throw RuntimeException("Interrupted while waiting for CocoaPods xcodebuild lock.", interrupted)
        }
    }

    private fun HeldLock.close() {
        lock.release()
        channel.close()
    }
}
