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
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.ui.tooling.preview.android)


    // Jetpack Compose libraries
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
    implementation(platform(libs.androidx.compose.bom))

    // ViewModel Dependencies
    implementation (libs.lifecycle.viewmodel.ktx)
    implementation (libs.lifecycle.livedata.ktx)
    implementation(libs.androidx.runtime.livedata)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    // Dependency Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //Corrutinas
    implementation (libs.kotlinx.coroutines.android)

    // Fragment
    implementation (libs.fragment.ktx)
    // Activity
    implementation (libs.activity.ktx)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))

}

kapt {
    correctErrorTypes = true
}