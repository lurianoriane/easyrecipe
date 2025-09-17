plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
}

kotlin {
    androidLibrary {
        namespace = "com.lurian.shared"
        compileSdk = 36
        minSdk = 24
    }
    sourceSets {
        commonMain.dependencies {
            implementation(project(":features:meal-type"))
            implementation(project(":network"))
            implementation(libs.koin.core)

        }
        androidMain.dependencies {
            implementation(project(":features:search"))
        }
    }
}