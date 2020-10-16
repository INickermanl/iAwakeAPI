plugins {
    id("java")
    id("kotlin")
    id("idea")
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

dependencies {
    implementation(Dependencies.javaxInject)
    implementation(Dependencies.kotlin_jdk)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.gson)
    compileOnly(Dependencies.slf4jAndroid)
}