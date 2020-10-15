import build.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    `java-library`
    id("kotlin")
    maven
    `maven-publish`
    jacoco
    id("org.jetbrains.dokka")
    id("com.jfrog.bintray")
    id("com.github.ben-manes.versions")
}

base.archivesBaseName = "log"
group = ProjectProperties.groupId
version = ProjectProperties.versionName

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions.jvmTarget = "1.8"

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit:junit:4.13.1")
    testImplementation("io.mockk:mockk:1.10.2")
}

tasks.named<DokkaTask>("dokkaHtml") {
    outputDirectory.set(File(projectDir, "../docs/log"))
}

tasks.create("sourcesJar", Jar::class) {
    dependsOn("classes")
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

artifacts {
    archives(tasks.named<Jar>("sourcesJar"))
}

uploadArchivesSettings()
publishingSettings("$buildDir/libs/${base.archivesBaseName}-${version}.jar")
bintraySettings()
jacocoSettings()
dependencyUpdatesSettings()
