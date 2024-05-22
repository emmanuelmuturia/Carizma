// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

    // The Gradle Plugin...
    alias(notation = libs.plugins.android.gradle.plugin) apply false

    // The Kotlin Plugin...
    alias(notation = libs.plugins.android.kotlin.plugin) apply false

    // The Security Plugin (AppSweep)...
    alias(notation = libs.plugins.appsweep) apply false

    // The Compiler Plugin Developer (KSP)...
    alias(notation = libs.plugins.ksp) apply false

    // The Google Plugins...
    alias(notation = libs.plugins.gms.google.services) apply false
    alias(notation = libs.plugins.firebase.crashlytics) apply false
    alias(notation = libs.plugins.secrets.gradle.plugin) apply false
    alias(notation = libs.plugins.firebase.performance) apply false

}