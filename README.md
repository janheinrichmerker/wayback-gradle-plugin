[![](https://jitpack.io/v/dev.reimer/wayback-gradle-plugin.svg)](https://jitpack.io/#dev.reimer/wayback-gradle-plugin)

# wayback-gradle-plugin<sup>[α](#status-α)</sup>

Gradle plugin for the [Internet Archive](https://archive.org)'s [Wayback API](https://archive.org/help/wayback_api.php),
powered by the [Java API wrapper](https://github.com/reimersoftware/wayback-api).

## Gradle Dependency

This library is available on [**jitpack.io**](https://jitpack.io/#dev.reimer/wayback-gradle-plugin).  
Add this in your `build.gradle.kts` or `build.gradle` file:

<details open><summary>Kotlin</summary>

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("dev.reimer:wayback-gradle-plugin:<version>")
}
```

</details>

<details><summary>Groovy</summary>

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'dev.reimer:wayback-gradle-plugin:<version>'
}
```

</details>

## Status α

⚠️ _Warning:_ This project is in an experimental alpha stage:
- The API may be changed at any time without further notice.
- Development still happens on `master`.
- Pull Requests are highly appreciated!
