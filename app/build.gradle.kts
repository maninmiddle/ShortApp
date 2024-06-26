plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.devToolsKsp)
    alias(libs.plugins.hiltDagger)
}

android {
    namespace = "com.maninmiddle.shortapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.maninmiddle.shortapp"
        minSdk = 24
        targetSdk = 34
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
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // dagger hilt
    implementation(libs.hiltAndroid)
    implementation(project(":data"))
    implementation(project(":domain"))
    ksp(libs.hiltCompiler)
    implementation(libs.dagger)
    ksp(libs.daggerCompiler)

    

    // retrofit
    implementation(libs.retrofit2)
    implementation(libs.moshi.converter)

    implementation(project(":presentation"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}