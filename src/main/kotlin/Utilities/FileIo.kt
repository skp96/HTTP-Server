package Utilities

import java.io.*
import java.nio.file.Files
import java.nio.file.Paths

class FileIo : FileInterface {
    override fun readResource(filePath: String): String {
        val path = Paths.get(filePath)
        val file = File(path.toUri())
        return file.readText(Charsets.UTF_8)
    }

    override fun writeResource(filePath: String, resource: String) {
        val path = Paths.get(filePath)
        val file = File(path.toUri())
        if (!file.exists()) {
            Files.createFile(path)
        }
        file.appendText(resource + "\n")
    }

    override fun readFile(fileName: String): String {
        return javaClass.classLoader.getResource(fileName).readText(Charsets.UTF_8)
    }

    override fun clearFile(filePath: String) {
        val path = Paths.get(filePath)
        val file = File(path.toUri())
        val fileWriter = PrintWriter(file)
        fileWriter.println("")
        fileWriter.close()
    }
}
