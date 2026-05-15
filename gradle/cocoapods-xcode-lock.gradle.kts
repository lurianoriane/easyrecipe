/**
 * Serialize CocoaPods `xcodebuild` work and guard the SQLite build DB.
 *
 * Every subproject that applies `org.jetbrains.kotlin.native.cocoapods` and declares
 * pods must apply this script; otherwise that project's `podBuild*` tasks can run in
 * parallel with each other or with another module and hit "build.db … database is locked".
 */
val cocoaPodsXcodeBuildService = gradle.sharedServices.registerIfAbsent(
    "cocoaPodsXcodeBuildService",
    CocoaPodsXcodeBuildService::class,
) {
    maxParallelUsages.set(1)
    parameters.lockFile.set(rootProject.layout.projectDirectory.file(".gradle/cocoapods-xcodebuild.lock"))
}

fun String.usesCocoaPodsXcodeBuildLock(): Boolean =
    this == "podGenIos" ||
        this == "podInstall" ||
        this == "podInstallSyntheticIos" ||
        startsWith("podSetupBuild") ||
        startsWith("podBuild")

tasks.configureEach {
    if (!name.usesCocoaPodsXcodeBuildLock()) return@configureEach

    usesService(cocoaPodsXcodeBuildService)
    doFirst("acquireCocoaPodsXcodeBuildLock") {
        cocoaPodsXcodeBuildService.get().acquire(path)
    }
    doLast("releaseCocoaPodsXcodeBuildLock") {
        cocoaPodsXcodeBuildService.get().release(path)
    }
}
