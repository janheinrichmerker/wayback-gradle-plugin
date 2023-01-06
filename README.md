[![GitHub Actions](https://img.shields.io/github/actions/workflow/status/heinrichreimer/wayback-gradle-plugin/gradle.yml?branch=master&style=flat-square)](https://github.com/heinrichreimer/wayback-gradle-plugin/actions/workflows/gradle.yml)
[![Gradle plugin portal](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/dev/reimer/wayback/dev.reimer.wayback.gradle.plugin/maven-metadata.xml.svg?label=gradle&style=flat-square)](https://plugins.gradle.org/plugin/dev.reimer.wayback)
[![JitPack](https://img.shields.io/jitpack/v/github/heinrichreimer/wayback-gradle-plugin?style=flat-square)](https://jitpack.io/#dev.reimer/wayback-gradle-plugin)

# 🕰️ wayback-gradle-plugin<sup>[α](#status-α)</sup>

Gradle plugin for the [Internet Archive](https://archive.org)'s [Wayback API](https://archive.org/help/wayback_api.php),
powered by the [Java API wrapper](https://github.com/reimersoftware/wayback-api).

## Gradle Dependency

The plugin is available from the Gradle [plugin portal](https://plugins.gradle.org/plugin/dev.reimer.wayback).  
Add this in your `build.gradle.kts` or `build.gradle` file:

<details open><summary>Kotlin</summary>

```kotlin
plugins {
  id("dev.reimer.wayback") version "<version>"
}
```

</details>

<details><summary>Groovy</summary>

```groovy
plugins {
  id "dev.reimer.wayback" version "<version>"
}
```

</details>

## Status α

⚠️ _Warning:_ This project is in an experimental alpha stage:
- The API may be changed at any time without further notice.
- Development still happens on `master`.
- Pull Requests are highly appreciated!
