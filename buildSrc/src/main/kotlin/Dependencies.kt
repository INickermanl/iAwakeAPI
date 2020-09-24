object Apps {
    const val compileSdk = 28
    const val buildToolsVersion = "30.0.1"
    const val minSdk = 21
    const val targetSdk = 28
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "4.0.1"
    const val kotlin = "1.4.0"
    const val appcompat = "1.0.2"
    const val coreKotlin = "1.1.0"
    const val constraintVersion = "2.0.0"
    const val daggerVersion = "2.29.1"
    const val timberVersion = "4.7.1"

    /* test */
    const val junit = "4.12"
    const val junitExt = "1.1.1"
    const val espresso = "3.2.0"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKotlin = "androidx.core:core-ktx:${Versions.coreKotlin}"
    const val daggerAndroid =  "com.google.dagger:dagger:${Versions.daggerVersion}"
    const val daggerCompiler =  "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    const val constraintVersion = "androidx.constraintlayout:constraintlayout:${Versions.constraintVersion}"

    object AnnotationProcessors{
        const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    }

}

object Maven {
    const val uri = "\"https://jitpack.io\""
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}