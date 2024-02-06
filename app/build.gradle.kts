plugins {
    alias(notation = libs.plugins.com.android.application)
    alias(notation = libs.plugins.org.jetbrains.kotlin.android)
    alias(notation = libs.plugins.com.google.devtools.ksp)
    alias(notation = libs.plugins.com.google.dagger.hilt.android.plugin)
    alias(notation = libs.plugins.com.google.gms.google.services)
    alias(notation = libs.plugins.com.google.firebase.crashlytics)
    alias(notation = libs.plugins.com.google.firebase.performance)
    alias(notation = libs.plugins.com.guardsquare.appsweep)
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
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Firebase...
    implementation(dependencyNotation = platform(libs.firebase.bom))
    implementation(dependencyNotation = libs.firebase.cloud.firestore)
    implementation(dependencyNotation = libs.firebase.analytics)
    implementation(dependencyNotation = libs.firebase.cloud.messaging)
    implementation(dependencyNotation = libs.firebase.performance)
    implementation(dependencyNotation = libs.firebase.appcheck.playintegrity)

    // Splash Screen API...
    implementation(dependencyNotation = libs.androidx.core.splashscreen)

    // Dagger-Hilt...
    implementation(dependencyNotation = libs.hilt.android)
    "ksp"(dependencyNotation = libs.hilt.android.compiler)
    implementation(dependencyNotation = libs.androidx.hilt.navigation.compose)

    // Navigation...
    implementation(dependencyNotation = libs.androidx.navigation.compose)

    // App Compat...
    implementation(dependencyNotation = libs.appcompat)

    // Room...
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    "ksp"(libs.room.compiler)

    // Swipe To Refresh (Accompanist)...
    implementation(dependencyNotation = libs.accompanist.swiperefresh)

    // Glide...
    implementation(dependencyNotation = libs.com.github.bumptech.glide.compose)

    // ExoPlayer (Media3)...
    implementation(dependencyNotation = libs.androidx.media3.exoplayer)

    // Timber...
    implementation(dependencyNotation = libs.timber)

    // Android...
    implementation(dependencyNotation = libs.androidx.core.ktx)
    implementation(dependencyNotation = libs.androidx.lifecycle.runtime.ktx)
    implementation(dependencyNotation = libs.lifecycle.runtime.compose)
    implementation(dependencyNotation = libs.androidx.activity.compose)
    implementation(dependencyNotation = platform(libs.androidx.compose.bom))
    implementation(dependencyNotation = libs.compose.ui)
    implementation(dependencyNotation = libs.compose.ui.graphics)
    implementation(dependencyNotation = libs.compose.ui.tooling.preview)
    implementation(dependencyNotation = libs.material)
    implementation(dependencyNotation = libs.material3)

    // Testing...
    testImplementation(dependencyNotation = libs.robolectric)
    testImplementation(dependencyNotation = libs.kotlinx.coroutines.test)
    testImplementation(dependencyNotation = libs.mockK)
    testImplementation(dependencyNotation = libs.junit)
    androidTestImplementation(dependencyNotation = libs.hilt.testing)
    androidTestImplementation(dependencyNotation = libs.androidx.junit)
    androidTestImplementation(dependencyNotation = libs.androidx.espresso.core)
    androidTestImplementation(dependencyNotation = platform(libs.androidx.compose.bom))
    androidTestImplementation(dependencyNotation = libs.compose.ui.test.junit4)
    debugImplementation(dependencyNotation = libs.compose.ui.tooling)
    debugImplementation(dependencyNotation = libs.compose.ui.test.manifest)
    debugImplementation(dependencyNotation = libs.leakCanary)

}