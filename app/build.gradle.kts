import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
//    alias(libs.plugins.google.services)  // Firebase
    alias(libs.plugins.ksp)              // Room compiler
}

val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) {
        load(file.inputStream())
    }
}

android {
    namespace = "com.example.btl"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.btl"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        localProperties.forEach { key, value ->
            val k = key.toString()
            if (k != "sdk.dir" && k != "flutter.sdk") {
                buildConfigField("String", k, "\"${value}\"")
            }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    // ==================== CÓ SẴN (giữ nguyên) ====================
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // ==================== NAVIGATION =============================
    implementation(libs.androidx.navigation.compose)

    // ==================== RETROFIT + OKHTTP + GSON ===============
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)
    implementation(libs.gson)

    // ==================== FIREBASE ===============================
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.messaging)

    // ==================== ROOM (Database) ========================
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // ==================== COROUTINES =============================
    implementation(libs.kotlinx.coroutines.android)

    // ==================== VICO (Chart/Biểu đồ) ==================
    implementation(libs.vico.compose.m3)

    // ==================== WORKMANAGER ============================
    implementation(libs.androidx.work.runtime)

    // ==================== ITEXT PDF ==============================
    implementation(libs.itext.pdf)

    // ==================== DATASTORE ==============================
    implementation(libs.androidx.datastore.preferences)

    // ==================== TESTING (giữ nguyên) ===================
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}