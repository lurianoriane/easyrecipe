plugins {
    kotlin("plugin.serialization")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
}

kotlin {
    androidLibrary {
        namespace = "com.lurian.search"
        compileSdk = 35

        packaging {
            resources {
                excludes += setOf(
                    "META-INF/DEPENDENCIES",
                    "META-INF/LICENSE.md",
                    "META-INF/LICENSE-notice.md"
                )
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.serialization)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(project(":network"))
            implementation(project(":features:meal-type"))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.lifecycle.runtime.ktx)
            implementation(libs.androidx.ui.graphics)
            implementation(libs.logging.interceptor)
            implementation(libs.coil)
            implementation(libs.coil.netwok)
            implementation(libs.koin.android)
            implementation(compose.preview)
            implementation(project(":design-system"))
        }
        androidUnitTest.dependencies {
            implementation(libs.androidx.ui.test.manifest)
            implementation(project(":testing"))
        }
        androidInstrumentedTest.dependencies {
            implementation(project(":android-testing"))
        }
    }
}

compose.resources {
    packageOfResClass = "com.lurian.search.generated.resources"
    publicResClass = true
}