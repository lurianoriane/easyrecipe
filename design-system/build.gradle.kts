plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
}

kotlin {
    androidLibrary {
        namespace = "com.lurian.designsystem"
        compileSdk = 34

        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true

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
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(libs.coil)
            implementation(libs.coil.netwok)
            api(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.navigation.compose)
        }

        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.lifecycle.runtime.ktx)
            implementation(libs.androidx.ui.graphics)
            implementation(compose.preview)
        }
    }
}

compose.resources {
    packageOfResClass = "com.lurian.designsystem.generated.resources"
    publicResClass = true
}

//dependencies {
//    implementation(libs.androidx.appcompat)
//    implementation(libs.androidx.palette.ktx)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//}