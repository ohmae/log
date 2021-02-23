import build.*
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("com.android.library")
    id("kotlin-android")
    maven
    `maven-publish`
    signing
    id("org.jetbrains.dokka")
    id("com.github.ben-manes.versions")
}

base.archivesBaseName = "log-android"
group = ProjectProperties.groupId
version = ProjectProperties.versionName

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(14)
        targetSdkVersion(30)
        versionCode = ProjectProperties.versionCode
        versionName = ProjectProperties.versionName
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":log"))
    implementation(kotlin("stdlib"))
    testImplementation("junit:junit:4.13.2")
}

tasks.named<DokkaTask>("dokkaHtml") {
    outputDirectory.set(File(projectDir, "../docs/android"))
}

tasks.named<DokkaTask>("dokkaJavadoc") {
    outputDirectory.set(File(buildDir, "docs/javadoc"))
}

tasks.create("javadocJar", Jar::class) {
    dependsOn("dokkaJavadoc")
    archiveClassifier.set("javadoc")
    from(File(buildDir, "docs/javadoc"))
}

tasks.create("sourcesJar", Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets["main"].java.srcDirs)
}

artifacts {
    archives(tasks.named<Jar>("sourcesJar"))
}

uploadArchivesSettings()
publishingSettings("$buildDir/outputs/aar/${base.archivesBaseName}-release.aar")
dependencyUpdatesSettings()
