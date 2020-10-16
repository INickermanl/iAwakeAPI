plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    defaultPublishConfig("debug")

    compileSdkVersion(Apps.compileSdk)
    buildToolsVersion(Apps.buildToolsVersion)

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        dataBinding = false
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.appcompat)
    implementation(Dependencies.recyclerView)
    implementation(Dependencies.constraint)
    implementation(Dependencies.biometric)
    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.design)
    implementation(Dependencies.gridlayout)
    implementation(Dependencies.viewpager2)
    implementation(Dependencies.cardview)
    implementation(Dependencies.slf4jAndroid)
    implementation(Dependencies.gson)
    implementation(Dependencies.inputMask)
    implementation(Dependencies.glide)
    implementation(Dependencies.fragment)
    implementation(Dependencies.fragmentKtx)
    api(Dependencies.threetenabp)
    implementation(Dependencies.androidKtx)
    implementation(Dependencies.dagger)
    kapt(Dependencies.daggerCompiler)
    testImplementation(TestDependencies.junit)
}