plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = AppConfig.applicationId
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Android.Versions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(mapOf("path" to ":domain")))
    implementation(Android.coreKtx)
    implementation(Android.lifecycleRuntimeKtx)

    implementation(Android.Compose.activity)
    implementation(Android.Compose.ui)
    implementation(Android.Compose.toolingPreview)
    implementation(Android.Compose.material3)
    implementation(Android.Compose.material)
    implementation(Android.Compose.navigation)
    implementation(Android.Compose.foundation)
//    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation(Android.Compose.icons)
    implementation(Android.Accompanist.systemUiController)

    implementation(Android.Hilt.android)
    implementation(Android.Hilt.navigationCompose)
    kapt(Android.Hilt.androidCompiler)

    implementation(Dependencies.Kotlin.datetime)

    testImplementation(Dependencies.Testing.jUnit)
    
    androidTestImplementation(Dependencies.Testing.androidJUnit)
    androidTestImplementation(Dependencies.Testing.espresso)
    androidTestImplementation(Dependencies.Testing.composeJUnit)

    debugImplementation(Android.Compose.tooling)
    debugImplementation(Android.Compose.uiTestManifest)
}

kapt {
    correctErrorTypes = true
}
