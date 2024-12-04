plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {

    namespace = "com.example.evesafe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.evesafe"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true

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
    packaging {
        resources {
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/NOTICE.md"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("androidx.sqlite:sqlite:2.3.1")
    implementation ("com.google.firebase:firebase-auth:23.1.0")
    implementation ("com.google.firebase:firebase-database:20.5.3")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    // Google Maps API
    implementation ("com.google.android.gms:play-services-maps:18.1.0")
// Permissions and UI
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation  ("com.google.firebase:firebase-database:21.0.0")

        implementation ("androidx.recyclerview:recyclerview:1.2.0")
    implementation ("com.sun.mail:android-mail:1.6.7")
    implementation ("com.sun.mail:android-activation:1.6.7")




}