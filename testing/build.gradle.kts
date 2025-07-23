plugins {
    id("config.android.library")
}

android {
    namespace = "com.lurian.testing"
}

dependencies {
    api(libs.junit)
    api(libs.coroutines.test)
    api(libs.mockk)
    api(libs.turbine)
}