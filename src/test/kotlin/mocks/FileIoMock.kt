package mocks

import Utilities.FileInterface

class FileIoMock : FileInterface {
    private var file = ""

    override fun readResource(filePath: String): String {
        return file
    }

    override fun writeResource(filePath: String, resource: String) {
        file += (resource + "\n")
    }

    override fun readFile(fileName: String): String {
        return javaClass.classLoader.getResource(fileName).readText(Charsets.UTF_8)
    }

    override fun clearFile(filePath: String) {
        file = ""
    }
}
