plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.kironstylo.weatherApp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kironstylo.weatherApp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures{
        compose = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.ui.tooling.preview.android)
    testImplementation(libs.junit)

    // Jetpack Compose libraries
    implementation(libs.androidx.material3)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    androidTestImplementation(platform(libs.androidx.compose.bom))


    // Fragment
    implementation (libs.fragment.ktx)
    // Activity
    implementation (libs.activity.ktx)
    // ViewModel
    implementation (libs.lifecycle.viewmodel.ktx)
    // LiveData
    implementation (libs.lifecycle.livedata.ktx)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    //Corrutinas
    implementation (libs.kotlinx.coroutines.android)

    // Dagger-hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)


    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

kapt {
    correctErrorTypes = true
}