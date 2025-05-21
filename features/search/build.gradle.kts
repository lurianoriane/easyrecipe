plugins {
    id("config.android.library")
    id("config.android.library.compose")
    id("config.android.hilt")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.lurian.features.search"
    compileSdk = 34

    defaultConfig {
        testInstrumentationRunner = "com.lurian.android_testing.runner.ApplicationTestRunner"
    }

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

    implementation(project(":network"))
    implementation(project(":design-system"))
    implementation(project(":features:meal-type"))
    androidTestImplementation(project(":android-testing"))
    debugImplementation(libs.androidx.ui.test.manifest)
}