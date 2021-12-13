package Utilities

class FileIo {
    fun readResource(fileName: String): String {
        return javaClass.classLoader.getResource(fileName).readText(Charsets.UTF_8)
    }
}
