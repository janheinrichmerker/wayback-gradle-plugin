import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    `java-gradle-plugin`
    `maven-publish`
}

group = "dev.reimer"
version = "0.1.0"

repositories {
    mavenCentral()
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
        version = "5869e598a6"
    )
    implementation(
        group = "com.squareup.okhttp3",
        name = "okhttp",
        version = "4.2.2"
    )
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
