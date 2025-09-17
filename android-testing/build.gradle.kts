plugins {
    id("config.android.library")
}

android {
    namespace = "com.lurian.android_testing"
}

dependencies {

    api(libs.androidx.test.runner)
    api(libs.androidx.junit)
    api(libs.androidx.test.rules)
    api(libs.junit.compose.ui.test)
    api(libs.mockk.android)
}