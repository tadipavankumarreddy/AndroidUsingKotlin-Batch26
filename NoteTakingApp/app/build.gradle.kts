plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // TODO 0.2 add the plugin
    id("com.google.devtools.ksp")
    // TODO : Step 2: add Google services Plugin
    id("com.google.gms.google-services")
}

android {
    namespace = "com.nareshtech.notetakingapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.nareshtech.notetakingapp"
        minSdk = 24
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    // TODO 1: Add Dependencies and KSP (Kotlin Symbol Processing - for Annotations)
    implementation("androidx.room:room-runtime:2.7.1")
    implementation(libs.play.services.auth)
    ksp("androidx.room:room-compiler:2.7.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0")
    // TODO: Step 3: add Firebase bom to keep all other firebase
    //  services upto date with latest version
    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))
    // TODO: Step 4: add Firebase auth dependency
    implementation("com.google.firebase:firebase-auth")
    implementation("androidx.credentials:credentials:1.5.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.5.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    //TODO:step 5: This is for Firestore database
    implementation("com.google.firebase:firebase-firestore")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}