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
    implementation(Dependencies.simpleXml) {
        exclude(group = "junit", module = "junit")
        exclude(group = "xpp3", module = "xpp3")
        exclude(group = "stax", module = "stax-api")
        exclude(group = "stax", module = "stax")
    }
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.gson)
    compileOnly(Dependencies.commonsCodec)
    compileOnly(Dependencies.slf4jAndroid)
}