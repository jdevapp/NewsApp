// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libs.androidGradlePlugin)
        classpath(Libs.Kotlin.gradlePlugin)
        classpath(Libs.Google.Hilt.gradlePlugin)
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}