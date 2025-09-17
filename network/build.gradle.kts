plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    kotlin("plugin.serialization")
}

kotlin {
    androidLibrary {
        namespace = "com.lurian.network"
        compileSdk = 36
        minSdk = 24
    }
    sourceSets {
        commonMain.dependencies {
            implementation(libs.serialization)
            implementation(libs.bundles.ktor.default)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.koin.core)
        }
    }
}