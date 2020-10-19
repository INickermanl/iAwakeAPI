plugins {
    id("java")
    id("kotlin")
    id("idea")
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
//    allWarningsAsErrors = isWarningsAsErrorsConfig
    jvmTarget = javaVersion
}

dependencies {
    implementation(project(":domain"))
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGson)
    implementation(Dependencies.kotlin_jdk)
    implementation(Dependencies.javaxInject)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)
}