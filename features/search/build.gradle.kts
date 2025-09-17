plugins {
    id("config.android.library")
    id("config.android.library.compose")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.lurian.features.search"
    compileSdk = 34

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
        animationsDisabled = true
    }

    packaging {
        resources {
            excludes += setOf("META-INF/DEPENDENCIES")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.serialization)
    implementation(libs.retrofit.core)
    implementation(libs.logging.interceptor)
    implementation(libs.coil)
    implementation(libs.coil.netwok)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose.viewmodel)

    implementation(project(":network"))
    implementation(project(":design-system"))
    implementation(project(":features:meal-type"))
    androidTestImplementation(project(":android-testing"))
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(project(":testing"))
}