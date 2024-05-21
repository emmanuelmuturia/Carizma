plugins {

    // The Gradle Plugin...
    alias(notation = libs.plugins.android.gradle.plugin)

    // The Kotlin Plugin...
    alias(notation = libs.plugins.android.kotlin.plugin)

    // The Security Plugin (AppSweep)...
    alias(notation = libs.plugins.appsweep)

    // The Compiler Plugin Developer (KSP)...
    alias(notation = libs.plugins.ksp)

    // The Google Plugins...
    alias(notation = libs.plugins.gms.google.services)
    alias(notation = libs.plugins.firebase.crashlytics)
    alias(notation = libs.plugins.secrets.gradle.plugin)
    alias(notation = libs.plugins.firebase.performance)

}

android {
    namespace = "emmanuelmuturia.carizma"
    compileSdk = 34

    defaultConfig {
        applicationId = "emmanuelmuturia.carizma"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles( files = arrayOf(
                getDefaultProguardFile(name = "proguard-android-optimize.txt"),
                "proguard-rules.pro"
                )
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // The Android Libraries (Kotlin)...
    implementation(dependencyNotation = libs.androidx.core.ktx)
    implementation(dependencyNotation = libs.androidx.lifecycle.runtime.ktx)
    implementation(dependencyNotation = libs.androidx.core.splashscreen)

    // The Jetpack Compose Libraries...
    implementation(dependencyNotation = libs.androidx.activity.compose)
    implementation(dependencyNotation = platform(libs.androidx.compose.bom))
    implementation(dependencyNotation = libs.androidx.compose.ui.ui)
    implementation(dependencyNotation = libs.androidx.compose.ui.graphics)
    implementation(dependencyNotation = libs.androidx.compose.ui.tooling.preview)
    implementation(dependencyNotation = libs.androidx.compose.material3)
    implementation(dependencyNotation = libs.lifecycle.runtime.compose)

    // Firebase...
    implementation(dependencyNotation = platform(libs.firebase.bom))
    implementation(dependencyNotation = libs.firebase.cloud.firestore)
    implementation(dependencyNotation = libs.firebase.analytics)
    implementation(dependencyNotation = libs.firebase.cloud.messaging)
    implementation(dependencyNotation = libs.firebase.performance)
    implementation(dependencyNotation = libs.firebase.appcheck.playintegrity)

    // The Navigation Library (Jetpack Compose)...
    implementation(dependencyNotation = libs.androidx.navigation.compose)

    // The Dependency Injection Library (Koin)...
    implementation(dependencyNotation = libs.koin.androidx.compose)

    // The Logging Library (Timber)...
    implementation(dependencyNotation = libs.timber)

    // The Memory Leak Detection Library (LeakCanary)...
    debugImplementation(dependencyNotation = libs.leak.canary)

    // The Local Storage Library (Room)...
    implementation(dependencyNotation = libs.androidx.room.runtime)
    implementation(dependencyNotation = libs.androidx.room.ktx)
    "ksp"(dependencyNotation = libs.androidx.room.compiler)

    // The Image Loading Library (Coil)...
    implementation(dependencyNotation = libs.coil)

    // The Kotlin Coroutines Library...
    implementation(dependencyNotation = libs.kotlinx.coroutines.core)
    implementation(dependencyNotation = libs.kotlinx.coroutines.android)

    // The Gemini API...
    implementation(dependencyNotation = libs.gemini.api)

    // The Exoplayer Library (Media3)...
    implementation(dependencyNotation = libs.androidx.media3.exoplayer)

    // The Testing Libraries...
    testImplementation(dependencyNotation = libs.junit)
    testImplementation(dependencyNotation = libs.robolectric)
    testImplementation(dependencyNotation = libs.mockK)
    testImplementation(dependencyNotation = libs.kotlinx.coroutines.test)
    testImplementation(dependencyNotation = libs.koin.test)
    testImplementation(dependencyNotation = libs.koin.test.junit)
    testImplementation(dependencyNotation = libs.androidx.room.testing)
    androidTestImplementation(dependencyNotation = libs.androidx.test.ext.junit)
    androidTestImplementation(dependencyNotation = libs.androidx.test.espresso.core)
    androidTestImplementation(dependencyNotation = platform(libs.androidx.compose.bom))
    androidTestImplementation(dependencyNotation = libs.androidx.compose.ui.test.junit4)
    debugImplementation(dependencyNotation = libs.androidx.compose.ui.tooling)
    debugImplementation(dependencyNotation = libs.androidx.compose.ui.test.manifest)

}