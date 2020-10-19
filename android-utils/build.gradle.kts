plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    defaultPublishConfig("debug")

    compileSdkVersion(AndroidConfig.compileSdkVersion)
    buildToolsVersion(AndroidConfig.buildToolsVersion)


    defaultConfig {
        minSdkVersion(AndroidConfig.minSdkVersion)
        targetSdkVersion(AndroidConfig.targetSdkVersion)
        multiDexEnabled = true
        versionCode = AndroidConfig.versionCode
        versionName = projectVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = javaVersion
    }

    buildFeatures {
        dataBinding = false
    }
}

dependencies {
    implementation(Dependencies.kotlin_jdk)
    implementation(Dependencies.appcompat)
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