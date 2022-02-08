object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.1.1"
    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val appcompat = "androidx.appcompat:appcompat:1.4.0"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.3"
        const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

        object Navigation {
            private const val version = "2.5.0-alpha01"
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val dynamicFeatures = "androidx.navigation:navigation-dynamic-features-fragment:$version"
            const val testing = "androidx.navigation:navigation-testing:$version"
            const val gradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }
        object Fragment {
            private const val version = "1.4.0-alpha03"
            const val ktx = "androidx.fragment:fragment-ktx:$version"
            const val testing = "androidx.fragment:fragment-testing:$version"
        }
        object Activity {
            const val ktx = "androidx.activity:activity-ktx"
        }
        object Legacy {
            private const val version = "1.0.0"
            const val supportV4 = "androidx.legacy:legacy-support-v4:$version"
            const val supportV13 = "androidx.legacy:legacy-support-v13:$version"
        }
        object Room {
            private const val version = "2.4.1"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val test = "androidx.room:room-testing:$version"
        }
        object Lifecycle {
            private const val version = "2.4.0"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val common = "androidx.lifecycle:lifecycle-common-java8:$version"
            const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }
        object Test {
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val coreKtx = "androidx.test:core-ktx:$version"
            const val rules = "androidx.test:rules:$version"
            const val archCore = "androidx.arch.core:core-testing:2.1.0"
            object Ext {
                private const val version = "1.1.2"
                const val junitKtx = "androidx.test.ext:junit-ktx:$version"
            }
            object Espresso {
                private const val version = "3.3.0"
                const val core = "androidx.test.espresso:espresso-core:$version"
                const val contrib = "androidx.test.espresso:espresso-contrib:$version"
            }
        }
    }
    object Kotlin {
        private const val version = "1.6.10"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Coroutines {
            private const val version = "1.5.2"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }
    object Google{
        const val services = "com.google.gms:google-services:4.3.8"
        const val material = "com.google.android.material:material:1.4.0"
        const val truth = "com.google.truth:truth:1.1.2"
        const val gson = "com.google.code.gson:gson:2.8.6"

        const val flexbox = "com.google.android.flexbox:flexbox:3.0.0"
        const val composeThemeAdapter = "com.google.android.material:compose-theme-adapter:1.0.5"

        object Hilt {
            private const val version = "2.39"
            const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
            const val android = "com.google.dagger:hilt-android:$version"
            const val compiler = "com.google.dagger:hilt-compiler:$version"
            const val testing = "com.google.dagger:hilt-android-testing:$version"
        }
    }
    object retrofit2 {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
        const val converterScalars = "com.squareup.retrofit2:converter-scalars:$version"
    }
    object okhttp3 {
        private const val version = "4.9.3"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }
    object Glide {
        private const val version = "4.12.0"
        const val library = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }
    object Test {
        const val junit = "junit:junit:4.13.1"
    }
}
