// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()


        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven {
            url = uri("https://dl.google.com/dl/android/maven2")
        }
    }
    dependencies {
        classpath(libs.build.logic.android.gradlePlugin)
        classpath(libs.build.logic.kotlin.gradlePlugin)
        classpath(libs.kotlin.serialization)
    }
}


plugins {
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
}


tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
