plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //Moshi kotlin codegen
    id("com.google.devtools.ksp")
    // Kotlin Parcelize - Parcleable data class
    id("kotlin-parcelize")
}

android {
    namespace = "com.firrizq.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.firrizq.myapplication"
        minSdk = 30
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // SplashScreen android 12
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Picasso - Image Loader
    implementation("com.squareup.picasso:picasso:2.8")

    // Moshi
    // Json Converter - serialize and deserialize objects to and from JSON
    // 1 of libraries JSON converters for data serialization
    implementation("com.squareup.moshi:moshi:1.14.0")

    // Logging Interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // kotlin codegen
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

    // Retrofit - Request HTTP Web Service
    // HTTP Client - Retrofit with OkHTTP
    // to retrieve and upload JSON (or other structured data) via a REST based webservice
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // liveData
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Fused Location
    implementation("com.google.android.gms:play-services-location:21.0.1")
}