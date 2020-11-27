import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

sourceSets {
    main {
        java.srcDirs("src")
    }
}


repositories {
    jcenter()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}