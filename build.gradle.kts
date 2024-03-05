import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22"
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "0.10.1"
    id("org.jetbrains.dokka") version "0.10.0"
    `maven-publish`
}

group = "dev.reimer"
version = "0.1.1"

repositories {
    jcenter()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(
        group = "org.jetbrains.kotlinx",
        name = "kotlinx-coroutines-core",
        version = "1.0.1"
    )
    implementation(
        group = "dev.reimer",
        name = "wayback-api",
        version = "5367e224"
    )
    implementation(
        group = "com.squareup.okhttp3",
        name = "okhttp",
        version = "4.2.2"
    )
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType<DokkaTask> {
        outputDirectory = "$buildDir/javadoc"
        outputFormat = "javadoc"
    }
}

pluginBundle {
    website = "https://github.com/reimersoftware/wayback-gradle-plugin"
    vcsUrl = "https://github.com/reimersoftware/wayback-gradle-plugin.git"
    tags = listOf(
        "wayback-gradle-plugin",
        "wayback-api",
        "internet-archive",
        "wayback-machine",
        "wayback",
        "wayback-machine-downloader",
        "gradle",
        "gradle-plugin"
    )
}

gradlePlugin {
    plugins {
        create(name) {
            id = "dev.reimer.wayback"
            displayName = "Wayback Gradle Plugin"
            description = "Plugin for the Internet Archive's Wayback API."
            implementationClass = "dev.reimer.wayback.gradle.plugin.WaybackPlugin"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
