package Utilities

interface FileInterface {
    fun readResource(filePath: String): String
    fun writeResource(filePath: String, resource: String)
    fun readFile(fileName: String): String
}
