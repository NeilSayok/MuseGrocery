import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {

    val localProperties = Properties()
    localProperties.load(FileInputStream(rootProject.file("local.properties")))

    signingConfigs {
        create("release") {
            storeFile = localProperties["RELEASE_STORE_FILE"]?.let { file(it) }
            storePassword = localProperties["RELEASE_STORE_PASSWORD"].toString()
            keyAlias = localProperties["RELEASE_KEY_ALIAS"].toString()
            keyPassword = localProperties["RELEASE_STORE_PASSWORD"].toString()
        }
    }

    packaging {
        dex {
            useLegacyPackaging = true
        }
        jniLibs {
            useLegacyPackaging = true
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/java")
        getByName("release").java.srcDirs("src/release/java")
        getByName("debug").java.srcDirs("src/debug/java")
    }

    namespace = "com.neilsayok.musewearables"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.neilsayok.musewearables"
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
            manifestPlaceholders["crashlyticsCollectionEnabled"] = "true"
            ext["enableCrashlytics"] = true
            ext["alwaysUpdateBuildId"] = true
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), file("proguard-rules.pro")
            )
            signingConfig = signingConfigs.getByName("release")
        }

        debug {
            manifestPlaceholders["crashlyticsCollectionEnabled"] = "false"
            ext["enableCrashlytics"] = false
            ext["alwaysUpdateBuildId"] = false
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), file("proguard-rules.pro")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
    }

    flavorDimensions.add("kpn")
    productFlavors {
        create("stage") {
            dimension = "kpn"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-stage"

            resValue("string", "app_name", "MuGrocery Stage")

            buildConfigField("String", "BFF_HOST", "\"http://ec2-65-0-128-18.ap-south-1.compute.amazonaws.com:3000/\"")
            buildConfigField("String", "DATA_STORE_KEY", "\"KPN_APP_DATASTORE_STAGE\"")

        }


        create("prod") {
            dimension = "kpn"
            resValue("string", "app_name", "MuGrocery")
            buildConfigField("String", "BFF_HOST", "\"http://ec2-65-0-128-18.ap-south-1.compute.amazonaws.com:3000/\"")
            buildConfigField("String", "DATA_STORE_KEY", "\"KPN_APP_DATASTORE_PROD\"")


        }
    }
}


dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    //data store
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.preferences.core)

    //compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation (libs.androidx.constraintlayout.compose)


    //paging 3
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)

    // SDP & SSP Dependencies
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)

    // Internet Calling Dependencies
    implementation(libs.converter.gson)
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.logging.interceptor)

    // Dependency injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.javax.inject)
    implementation(libs.androidx.hilt.navigation.compose)



    //Image Loader
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)


    //Payment
    implementation (libs.stripe.android)


    //test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    //debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Flipper
    debugImplementation(libs.flipper)
    debugImplementation(libs.soloader)
    debugImplementation(libs.flipper.network.plugin)
}
