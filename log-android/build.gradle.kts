import build.ProjectProperties
import build.dependencyUpdatesSettings
import build.publishingSettings
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("com.android.library")
    id("kotlin-android")
    `maven-publish`
    signing
    id("org.jetbrains.dokka")
    id("com.github.ben-manes.versions")
}

base.archivesName.set("log-android")
group = ProjectProperties.groupId
version = ProjectProperties.versionName

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 14
        targetSdk = 30
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

publishingSettings("$buildDir/outputs/aar/${base.archivesName.get()}-release.aar")
dependencyUpdatesSettings()
