const val projectVersion: String = "1.0"
const val kotlinVersion: String = "1.4.0"
const val isWarningsAsErrorsConfig = true
const val javaVersion = "1.8"

object AndroidConfig {
    const val buildToolsVersion = "29.0.3"
    const val minSdkVersion = 21
    const val targetSdkVersion = 29
    const val compileSdkVersion = 29
    val versionCode = "1"
    const val testInstRunner = "android.support.test.runner.AndroidJUnitRunner"
}

const val roomVersion = "2.2.5"
const val daggerVersion = "2.27"
const val moxyVersion = "2.0.2"
const val coroutineVersion = "1.3.5"
const val fragmentVersion = "1.2.3"
const val retrofitVersion = "2.7.1"

object Dependencies {
    const val scalarConverter = "com.squareup.retrofit2:converter-scalars:2.1.0"
    const val scalar = "com.squareup.retrofit2:retrofit:2.1.0"
    const val kotlin_jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val kotlin_serialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.10.1"
    const val fragment = "androidx.fragment:fragment:$fragmentVersion"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragmentVersion"
    const val appcompat = "androidx.appcompat:appcompat:1.1.0"
    const val supportV4 = "androidx.legacy:legacy-support-v4:1.0.0"
    const val gridlayout = "androidx.gridlayout:gridlayout:1.0.0"
    const val drawerlayout = "androidx.drawerlayout:drawerlayout:1.1.0-rc01"
    const val biometric = "androidx.biometric:biometric:1.0.1"
    const val design = "com.google.android.material:material:1.1.0"
    const val cardview = "androidx.cardview:cardview:1.0.0"
    const val exifinterface = "androidx.exifinterface:exifinterface:1.2.0"
    const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
    const val androidKtx = "androidx.core:core-ktx:1.3.1"
    const val constraint = "androidx.constraintlayout:constraintlayout:1.1.3"
    const val room = "androidx.room:room-common:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val flexible = "eu.davidea:flexible-adapter:5.1.0"
    const val okhttpConnection = "com.squareup.okhttp3:okhttp-urlconnection:3.4.1"
    const val okhttp = "com.squareup.okhttp3:okhttp:4.9.0"
    const val flexibleUi = "eu.davidea:flexible-adapter-ui:1.0.0"
    const val touchImageView = "com.github.MikeOrtiz:TouchImageView:2.2.0"
    const val pageindicatorview = "com.romandanylyk:pageindicatorview:1.0.3"
    const val glide = "com.github.bumptech.glide:glide:4.11.0"
    const val nfcReader = "com.github.pro100svitlo:creditCardNfcReader:1.0.3"
    const val threetenabp = "com.jakewharton.threetenabp:threetenabp:1.2.1"
    const val swipeLayout = "com.daimajia.swipelayout:library:1.2.0@aar"
    const val circleimageview = "de.hdodenhof:circleimageview:3.0.1"
    const val fastscroll = "com.simplecityapps:recyclerview-fastscroll:2.0.0"
    const val googleServicesBase = "com.google.android.gms:play-services-base:17.2.1"
    const val googleServicesIdentity = "com.google.android.gms:play-services-identity:17.0.0"
    const val googleServicesAuth = "com.google.android.gms:play-services-auth:18.0.0"
    const val googleServicesAuthApiPhone =
        "com.google.android.gms:play-services-auth-api-phone:17.4.0"
    const val googleServicesMaps = "com.google.android.gms:play-services-maps:17.0.0"
    const val mapsUtils = "com.google.maps.android:android-maps-utils:0.6.2"
    const val slf4jAndroid = "com.arcao:slf4j-timber:3.1"
    const val commonsCodec = "commons-codec:commons-codec:1.3"
    const val commonsIO = "commons-io:commons-io:2.5"
    const val ksoap2 = "com.google.code.ksoap2-android:ksoap2-android:3.6.4"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val loggingInterceptor = "com.github.ihsanbal:LoggingInterceptor:3.0.0"
    const val aspectj = "org.aspectj:aspectjrt:1.9.5"
    const val goldfinger = "co.infinum:goldfinger:1.1.1" //has 2.0.0
    const val reprint = "com.github.ajalt.reprint:core:3.3.2@aar"
    const val simpleXml = "org.simpleframework:simple-xml:2.7.1"
    const val gson = "com.google.code.gson:gson:2.8.6"
    const val card_io = "io.card:android-sdk:5.5.1"
    const val slf4jApi = "org.slf4j:slf4j-api:1.7.29"
    const val slf4jLog = "org.slf4j:slf4j-log4j12:1.7.29"
    const val javaxInject = "javax.inject:javax.inject:1"
    const val lottie = "com.airbnb.android:lottie:3.3.1"
    const val dagger = "com.google.dagger:dagger:$daggerVersion"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    const val qrGen = "com.github.kenglxn.QRGen:android:2.6.0"
    const val shimmer = "com.facebook.shimmer:shimmer:0.5.0"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging:20.2.3"
    const val firebaseMlVision = "com.google.firebase:firebase-ml-vision:24.0.3"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics:17.4.4"
    const val firebaseAnalyticsKtx = "com.google.firebase:firebase-analytics-ktx:17.4.4"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics:17.1.1"
    const val cameraKit = "com.otaliastudios:cameraview:2.1.0"
    const val lifecycle = "android.arch.lifecycle:extensions:2.1.0"
    const val openCvStub = "org.opencv:opencv-android:1.0.1"
    const val openCvLibrary = "org.opencv:openCVLibrary:3.4.0"
    const val javaxAnnotation = "javax.annotation:jsr250-api:1.0"
    const val moxy = "com.github.moxy-community:moxy:$moxyVersion"
    const val moxyCompiler = "com.github.moxy-community:moxy-compiler:$moxyVersion"
    const val moxyAndroid = "com.github.moxy-community:moxy-android:$moxyVersion"
    const val moxyAndroidx = "com.github.moxy-community:moxy-androidx:$moxyVersion"
    const val moxyKtx = "com.github.moxy-community:moxy-ktx:$moxyVersion"
    const val inputMask = "com.redmadrobot:input-mask-android:6.0.0"
    const val cicerone = "ru.terrakok.cicerone:cicerone:5.1.1"
    const val keyboardHeightProvider = "com.hold1:keyboardheightprovider:0.0.9"
    const val palette = "androidx.palette:palette:1.0.0"
    const val threetenbp = "org.threeten:threetenbp:1.4.1"
}

const val junitVersion = "4.12"

object TestDependencies {
    const val junit = "junit:junit:$junitVersion"
    const val mockk = "io.mockk:mockk:1.9.3"
    const val kotlin_test = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion"
}