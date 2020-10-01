plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        multiDexEnabled = true
        setProperty("archivesBaseName", "$applicationId-v$versionName($versionCode)")
        resConfigs("en")
        ndk {
            abiFilters("armeabi-v7a", "x86", "arm64-v8a", "x86_64")
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    bundle {
        language { enableSplit = true }
        density { enableSplit = true }
        abi { enableSplit = true }
    }

    lintOptions {
        isAbortOnError = false
        isIgnoreWarnings = true
        isQuiet = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    dependencies {
        implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
        implementation(Libs.kotlin)
        implementation(Libs.appcompat)
        implementation(Libs.coreKotlin)
        implementation(Libs.daggerAndroid)
        implementation(Libs.daggerCompiler)
        implementation(Libs.coroutinesCore)
        implementation(Libs.coroutinesAndroid)
        implementation(Dependencies.moxyAndroidx)
        implementation(Dependencies.moxyAndroid)
        implementation("com.neenbedankt.gradle.plugins:android-apt:1.4")
        implementation(Dependencies.moxy)
        kapt(Dependencies.moxyCompiler)
        implementation(Dependencies.moxyKtx)
        implementation(Libs.timber)
        implementation(Dependencies.cicerone)
        implementation(Libs.AnnotationProcessors.daggerProcessor)
        implementation(Libs.constraintVersion)
        testImplementation(TestLibs.junit)
        androidTestImplementation(TestLibs.junitExt)
        androidTestImplementation(TestLibs.espresso)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }

    packagingOptions {
        exclude("META-INF/proguard/androidx-annotations.pro")
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/gradle/incremental.annotation.processors")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/metadata.kotlin_module")
        exclude("META-INF/license.txt")
        exclude("META-INF/metadata.jvm.kotlin_module")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
    }

    kapt {
        javacOptions {
            option("-Xmaxerrs", 99999)
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }

    buildTypes {
        get("debug").apply {
            buildConfigField("Boolean", "BEBUG_BUILD_TYPE", "true")
        }
        get("release").apply {
            buildConfigField("Boolean", "BEBUG_BUILD_TYPE", "false")
        }
    }
}
dependencies {
    implementation(project(mapOf("path" to ":bl")))
}
