import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import java.io.File

// Android Studio calls syncFramework with Kotlin/CocoaPods properties, while Compose Resources
// expects either Xcode environment values or compose.ios.resources.* properties.
abstract class SyncCocoaPodsComposeResourcesTask : DefaultTask() {
    @get:Optional
    @get:Input
    abstract val platform: Property<String>

    @get:Optional
    @get:Input
    abstract val archs: ListProperty<String>

    @get:Input
    abstract val resourceRootsByTarget: MapProperty<String, String>

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val resourceRoots: ConfigurableFileCollection

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun syncResources() {
        val resolvedPlatform = platform.orNull ?: missing("platform")
        val resolvedArchs = archs.orNull?.takeIf { it.isNotEmpty() } ?: missing("architectures")
        val resourceRoots = resourceRootsByTarget.get()
        val output = outputDir.get().asFile

        output.deleteRecursively()
        output.mkdirs()

        requestedIosTargets(resolvedPlatform, resolvedArchs).forEach { target ->
            val root = resourceRoots[target]?.let(::File)
            if (root?.isDirectory != true) return@forEach

            root.walkTopDown()
                .filter { it.isFile && !it.isHidden }
                .forEach { source ->
                    val destination = output.resolve(source.relativeTo(root))
                    if (!destination.exists()) {
                        destination.parentFile.mkdirs()
                        source.copyTo(destination)
                    }
                }
        }
    }

    private fun missing(attribute: String): Nothing =
        error(
            "Could not infer iOS target $attribute. Provide compose.ios.resources.*," +
                " Xcode PLATFORM_NAME/ARCHS, or kotlin.native.cocoapods.platform/archs."
        )

    private fun requestedIosTargets(platform: String, archs: List<String>): List<String> =
        when {
            platform.startsWith("iphoneos") -> archs.map { arch ->
                when (arch) {
                    "arm64", "arm64e" -> "iosArm64"
                    else -> error("Unknown iOS device arch: '$arch'")
                }
            }

            platform.startsWith("iphonesimulator") -> archs.map { arch ->
                when (arch) {
                    "arm64", "arm64e" -> "iosSimulatorArm64"
                    "x86_64" -> "iosX64"
                    else -> error("Unknown iOS simulator arch: '$arch'")
                }
            }

            else -> error("Unknown iOS platform: '$platform'")
        }.distinct()
}

val hasComposeResourceTarget = providers.provider {
    providers.gradleProperty("compose.ios.resources.platform").isPresent &&
        providers.gradleProperty("compose.ios.resources.archs").isPresent
}

val hasXcodeResourceTarget = providers.provider {
    providers.environmentVariable("PLATFORM_NAME").isPresent &&
        providers.environmentVariable("ARCHS").isPresent
}

val hasCocoaPodsResourceTarget = providers.provider {
    providers.gradleProperty("kotlin.native.cocoapods.platform").isPresent &&
        providers.gradleProperty("kotlin.native.cocoapods.archs").isPresent
}

val syncComposeResourcesFromCocoaPodsProperties = providers.provider {
    !hasComposeResourceTarget.get() && !hasXcodeResourceTarget.get() && hasCocoaPodsResourceTarget.get()
}

val cocoaPodsPlatform = providers.gradleProperty("kotlin.native.cocoapods.platform")
val cocoaPodsArchs = providers.gradleProperty("kotlin.native.cocoapods.archs")
    .map { archs -> archs.split(',', ' ').filter { it.isNotBlank() } }

val cocoaPodsComposeResourceRoots = mapOf(
    "iosArm64" to layout.buildDirectory.dir("kotlin-multiplatform-resources/aggregated-resources/iosArm64"),
    "iosSimulatorArm64" to layout.buildDirectory.dir("kotlin-multiplatform-resources/aggregated-resources/iosSimulatorArm64"),
    "iosX64" to layout.buildDirectory.dir("kotlin-multiplatform-resources/aggregated-resources/iosX64"),
)

val syncCocoaPodsComposeResourcesForIos by tasks.registering(SyncCocoaPodsComposeResourcesTask::class) {
    onlyIf { syncComposeResourcesFromCocoaPodsProperties.get() }
    dependsOn(
        "iosArm64AggregateResources",
        "iosSimulatorArm64AggregateResources",
        "iosX64AggregateResources",
    )

    platform.set(cocoaPodsPlatform)
    archs.set(cocoaPodsArchs)
    outputDir.set(layout.buildDirectory.dir("compose/cocoapods/compose-resources"))
    resourceRoots.from(cocoaPodsComposeResourceRoots.values)
    resourceRootsByTarget.set(
        providers.provider {
            cocoaPodsComposeResourceRoots.mapValues { (_, directory) ->
                directory.get().asFile.absolutePath
            }
        }
    )
}

tasks.named("syncPodComposeResourcesForIos") {
    onlyIf { !syncComposeResourcesFromCocoaPodsProperties.get() }
}

tasks.configureEach {
    if (name == "syncFramework") {
        dependsOn(syncCocoaPodsComposeResourcesForIos)
    }
}
