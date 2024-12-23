plugins {
    kotlin("jvm") version "2.1.0"
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.3.0"
    id("org.jetbrains.dokka") version "0.10.0"
    `maven-publish`
    id("com.palantir.git-version") version "3.1.0"
}

val gitVersion: groovy.lang.Closure<String> by extra
group = "dev.reimer"
version = gitVersion()

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    implementation("dev.reimer:wayback-api:22d527e")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}

lateinit var javadocJar: TaskProvider<Jar>
lateinit var sourcesJar: TaskProvider<Jar>

tasks {
    // Include project license in generated JARs.
    withType<Jar> {
        from(project.projectDir) {
            include("LICENSE")
            into("META-INF")
        }
    }

    // Generate Kotlin/Java documentation from sources.
    dokka {
        outputFormat = "html"
    }

    // JAR containing Kotlin/Java documentation.
    javadocJar = register<Jar>("javadocJar") {
        group = JavaBasePlugin.DOCUMENTATION_GROUP
        dependsOn(dokka)
        from(dokka)
        archiveClassifier.set("javadoc")
    }

    // JAR containing all source files.
    sourcesJar = register<Jar>("sourcesJar") {
        from(sourceSets.main.get().allSource)
        archiveClassifier.set("sources")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(sourcesJar.get())
            artifact(javadocJar.get())
        }
    }
}

gradlePlugin {
    website = "https://github.com/reimersoftware/wayback-gradle-plugin"
    vcsUrl = "https://github.com/reimersoftware/wayback-gradle-plugin.git"
    plugins {
        create(name) {
            id = "dev.reimer.wayback"
            implementationClass = "dev.reimer.wayback.gradle.plugin.WaybackPlugin"
            displayName = "Wayback Gradle Plugin"
            description = "Plugin for the Internet Archive's Wayback API."
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
    }
}
