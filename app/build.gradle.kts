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
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.vectordrawable)

    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.livedata)
    implementation(libs.viewmodel)

    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.androidx.multidex)

    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.stetho.okhttp3)
    implementation(libs.review.ktx)
    implementation(libs.compose.theme.adapter)
    implementation(libs.grid)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.ui)
    implementation(libs.androidx.animation)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.ui.tooling.preview)
    implementation(libs.ui.tooling)
    implementation(libs.androidx.ui.util)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.accompanist.appcompat.theme)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.accompanist.pager)
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    implementation(libs.easypermissions)
    implementation(libs.lottie.compose)
    implementation(libs.datetime)

    implementation(libs.core)
    coreLibraryDesugaring(libs.desugar.jdk.libs)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.preferences.core)
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.ui.graphics)

    implementation(libs.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)
    implementation(libs.converter.gson)
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.hilt.android)

    kapt(libs.hilt.android.compiler)
    implementation(libs.javax.inject)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.app.update)
    implementation(libs.app.update.ktx)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.ui.test.junit4)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.exifinterface)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.truth)
    testImplementation(libs.json)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockwebserver)


    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)

    debugImplementation(libs.androidx.compose.ui.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    debugImplementation(libs.flipper.network.plugin)
    debugImplementation(libs.flipper)
    debugImplementation(libs.soloader)
}