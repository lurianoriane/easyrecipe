plugins {
    id("config.android.library")
    id("config.android.hilt")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.lurian.network"
    compileSdk = 34

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", "\"https://dummyjson.com/recipes/\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://dummyjson.com/recipes/\"")
        }
    }

}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.kotlin.serialization)
    implementation(libs.logging.interceptor)//okhttp
    implementation(libs.serialization)
}