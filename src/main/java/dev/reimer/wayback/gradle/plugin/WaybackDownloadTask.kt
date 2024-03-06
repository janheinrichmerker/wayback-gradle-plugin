package dev.reimer.wayback.gradle.plugin

import dev.reimer.wayback.api.WaybackApi
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.net.URL
import java.net.URI
import java.time.LocalDateTime


abstract class WaybackDownloadTask : DefaultTask() {
    private val api = WaybackApi()

    @get:Input
    abstract var url: URI

    @get:Optional
    @get:Input
    var timestamp: LocalDateTime? = null

    @get:OutputFile
    abstract var destination: File

    @TaskAction
    fun download() = runBlocking {
        val snapshot = api.available(url.toURL(), timestamp)
            .archivedSnapshots
            .closest
            ?: throw GradleException(
                "Snapshot for url $url not found in the Web Archive."
            )
        println("Downloading snapshot to ${destination.path}.")
        snapshot.downloadTo(destination)
    }
}