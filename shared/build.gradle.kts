plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.cocoapods)
}

kotlin {
    androidLibrary {
        namespace = "com.lurian.shared"
        compileSdk = 36
        minSdk = 24
    }
    val iosTargets = listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    )

    iosTargets.forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
            binaryOption("bundleId", "com.lurian.easyrecipe.shared")
        }
    }

    cocoapods {
        version = "1.0.0"
        summary = "Shared UI and business logic for EasyRecipe."
        homepage = "https://example.com/easyrecipe"
        ios.deploymentTarget = "15.0"
        podfile = project.file("../iosApp/Podfile")

        framework {
            baseName = "shared"
            isStatic = true
            binaryOption("bundleId", "com.lurian.easyrecipe.shared")
        }
    }

    jvm {
        compilations.configureEach {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
                }
            }
        }
    }
    sourceSets {
        commonMain.dependencies {
            implementation(project(":features:meal-type"))
            implementation(project(":network"))
            implementation(project(":features:search"))
            implementation(project(":design-system"))
            implementation(libs.koin.core)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            api(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
    }
}

apply(from = rootProject.file("gradle/cocoapods-xcode-lock.gradle.kts"))
apply(from = rootProject.file("gradle/ios-cocoapods-build-support.gradle.kts"))
