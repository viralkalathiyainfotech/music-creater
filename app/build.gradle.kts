plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id ("kotlin-parcelize")
    id("kotlin-kapt")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.musiccreater"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.musiccreater"
        minSdk = 24
        targetSdk = 36
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
    kotlinOptions {
        jvmTarget = "11"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.play.services.cast.framework)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore.ktx)

    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.lifecycle.viewmodel.ktx)

    implementation(libs.dexter)
    implementation(libs.gson)
    implementation(libs.roundedimageview)
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)
    implementation(libs.glide)

    implementation(libs.logger)

    implementation(libs.pinview)

    val room_version = "2.7.2"

    implementation("androidx.room:room-runtime:$room_version")
    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    // See Add the KSP plugin to your project
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:${room_version}")
    implementation("com.github.appsfeature:otp-view:1.1")



    implementation(libs.ultimatebarx)

    //    Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("com.squareup.retrofit2:adapter-rxjava2:3.0.0")
    implementation("io.reactivex.rxjava2:rxandroid:2.0.2")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    implementation("com.google.android.gms:play-services-location:21.0.1")

    implementation("com.airbnb.android:lottie:6.6.7")
    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation("com.squareup.picasso:picasso:2.8")
    implementation("com.google.android.material:material:1.12.0")

    //firebase
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation(platform("com.google.firebase:firebase-bom:32.2.0"))

    implementation("com.github.sina-seyfi:AdvancedCardView:1.0.1")
    implementation("com.github.cachapa:ExpandableLayout:2.9.2")
}