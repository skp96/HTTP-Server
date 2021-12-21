package Utilities

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class FileIo {
    fun readResource(filePath: String): String {
        val path = Paths.get(filePath)
        val file = File(path.toUri())
        return file.readText(Charsets.UTF_8)
    }

    fun writeResource(filePath: String, resource: String) {
        val path = Paths.get(filePath)
        val file = File(path.toUri())
        if (!file.exists()) {
            Files.createFile(path)
        }
        file.writeText(resource)
    }
}
