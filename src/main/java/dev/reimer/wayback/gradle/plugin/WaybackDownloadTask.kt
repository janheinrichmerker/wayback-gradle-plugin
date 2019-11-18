package dev.reimer.wayback.gradle.plugin

import dev.reimer.wayback.api.WaybackApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.sink
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.net.URL
import java.util.*


abstract class WaybackDownloadTask : DefaultTask() {
    private val api = WaybackApi()

    @get:Input
    abstract var url: URL

    @get:Optional
    @get:Input
    var timestamp: Date? = null

    @get:Input
    @get:OutputFile
    abstract var destination: File

    @TaskAction
    fun download() = runBlocking {
        val snapshot = api.available(url, timestamp)
            .archivedSnapshots
            .closest
            ?: throw GradleException(
                "Snapshot for url $url not found in the Web Archive."
            )
        val url = snapshot.url
        downloadUrl(url)
        println("Downloading snapshot to ${destination.path}")
    }

    private fun downloadUrl(url: URL) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()

        // Clear output file.
        destination.delete()
        destination.createNewFile()

        // Copy response stream.
        response.body?.use { body ->
            body.source().use { source ->
                destination.sink().use { sink ->
                    source.readAll(sink)
                }
            }
        }
    }
}