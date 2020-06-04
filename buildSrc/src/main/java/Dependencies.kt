object Apps {
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "4.0.0"
    const val kotlin = "1.3.72"
    const val appcompat = "1.0.2"
    const val coreKotlin = "1.1.0"
    const val constraintVersion = "1.1.3"

    /* test */
    const val junit = "4.12"
    const val junitExt = "1.1.1"
    const val espresso = "3.2.0"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKotlin = "androidx.core:core-ktx:${Versions.coreKotlin}"
    const val constraintVersion = "androidx.constraintlayout:constraintlayout:${Versions.constraintVersion}"

}

object Maven {
    const val uri = "\"https://jitpack.io\""
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}