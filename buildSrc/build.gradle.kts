plugins {
    kotlin("jvm") version "1.5.30"
    `kotlin-dsl`
}

repositories {
    google()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.android.tools.build:gradle:7.0.1")
    implementation("com.github.ben-manes:gradle-versions-plugin:0.39.0")
}
