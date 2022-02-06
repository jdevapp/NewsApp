plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Config.COMPILE_SDK
    buildToolsVersion = Config.BUILD_TOOLS

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
        }
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

}

dependencies {
    // AndroidX
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.constraintlayout)
    implementation(Libs.AndroidX.swiperefreshlayout)
    implementation(Libs.AndroidX.Navigation.fragmentKtx)
    implementation(Libs.AndroidX.Navigation.uiKtx)
    implementation(Libs.AndroidX.Navigation.dynamicFeatures)
    implementation(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.ktx)
    kapt(Libs.AndroidX.Room.compiler)

    // Google
    implementation(Libs.Google.material)
    implementation(Libs.Google.Hilt.android)
    kapt(Libs.Google.Hilt.compiler)

    // Kotlin
    implementation(Libs.Kotlin.Coroutines.core)
    implementation(Libs.Kotlin.Coroutines.android)

    // Retrofit2
    implementation(Libs.retrofit2.retrofit)
    implementation(Libs.retrofit2.converterGson)
    implementation(Libs.retrofit2.converterScalars)
    implementation(Libs.okhttp3.loggingInterceptor)


    // Dependencies for local unit tests
    testImplementation(Libs.Test.junit)
    testImplementation(Libs.AndroidX.Navigation.test)
    testImplementation(Libs.AndroidX.Room.test)
    testImplementation(Libs.Kotlin.Coroutines.android)
    testImplementation(Libs.Kotlin.Coroutines.test)
    testImplementation(Libs.Google.truth)
    kaptTest(Libs.Google.Hilt.compiler)


    // AndroidX Test - JVM testing
    testImplementation(Libs.AndroidX.Test.Ext.junitKtx)
    // AndroidX Test - Instrumented testing
    androidTestImplementation(Libs.AndroidX.Test.Ext.junitKtx)
    androidTestImplementation(Libs.AndroidX.Test.Espresso.core)
    androidTestImplementation(Libs.AndroidX.Navigation.test)
    kaptAndroidTest(Libs.Google.Hilt.compiler)



}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}