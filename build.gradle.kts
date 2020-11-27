// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
        maven("https://kotlin.bintray.com/kotlinx")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri(Maven.uri) }
    }
}

tasks.register("cleanBuildCash", Delete::class) {
    group = "cleanup"
    description = "delete build cash"
    delete(rootProject.buildDir)
    delete("${rootProject.projectDir}\\bl\\build")
    delete("${rootProject.projectDir}\\android-utils\\build")
    delete("${rootProject.projectDir}\\data\\build")
    delete("${rootProject.projectDir}\\domain\\build")
    delete("${rootProject.projectDir}\\presentation\\build")
    delete("${rootProject.projectDir}\\services\\build")
    delete("${rootProject.projectDir}\\buildSrc\\build")
    delete("${rootProject.projectDir}\\buildSrc\\.gradle")
}