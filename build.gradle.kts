// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

    // The Gradle Plugin...
    alias(notation = libs.plugins.android.gradle.plugin) apply false

    // The Kotlin Plugin...
    alias(notation = libs.plugins.android.kotlin.plugin) apply false

    // The Compose Compiler Gradle Plugin...
    alias(notation = libs.plugins.compose.compiler) apply false

}