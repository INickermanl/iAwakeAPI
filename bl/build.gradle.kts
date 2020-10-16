plugins {
    kotlin("jvm")
    id("kotlin-kapt")
    id("idea")
    kotlin("kapt")
    jacoco
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget  = javaVersion
}

jacoco {
    toolVersion = "0.8.5"
}
dependencies{
    implementation(project(":domain"))
    implementation(Dependencies.kotlinReflect)
    implementation(Dependencies.cicerone)
    implementation(Dependencies.kotlin_jdk)
    implementation(Dependencies.slf4jAndroid)
    implementation(Libs.kotlin)
    implementation(Libs.appcompat)
    implementation(Libs.coreKotlin)
    implementation(Libs.daggerAndroid)
    implementation(Libs.daggerCompiler)
    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)
    implementation(Dependencies.moxyAndroidx)
    implementation(Dependencies.moxy)
    kapt(Dependencies.moxyCompiler)
    implementation(Dependencies.moxyKtx)
    implementation(Dependencies.moxyAndroid)
}