
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
//    id("config.android.hilt")
}

kotlin {
    androidLibrary{
        namespace = "com.lurian.meal_type"
        compileSdk = 34
    }
    sourceSets{
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)
        }
    }
}