plugins {
    id("config.android.library")
    id("config.android.library.compose")
}

android {
    namespace = "com.lurian.designsystem"
    compileSdk = 34

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.material3.android)
    implementation(libs.bundles.coil.default)
    implementation(libs.androidx.palette.ktx)
    implementation(libs.androidx.ui.graphics.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}