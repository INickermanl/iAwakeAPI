plugins {
    kotlin("jvm")
    id("idea")
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
//    allWarningsAsErrors = isWarningsAsErrorsConfig
    jvmTarget = javaVersion
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(Dependencies.kotlin_jdk)
    implementation(Dependencies.slf4jAndroid)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGson)
    implementation(Dependencies.javaxInject)
    implementation(Dependencies.coroutinesCore)
}