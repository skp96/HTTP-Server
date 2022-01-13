import Utilities.FileIo
import java.io.FileNotFoundException
import java.lang.NullPointerException
import kotlin.test.*

class FileIoTest {
    @Test
    fun `readResource is able to read from a file`() {
        val fileIo = FileIo()
        val expectedResult = "Test\nFile\n"
        assertEquals(expectedResult, fileIo.readResource("src/test/kotlin/resources/runtime-test-file.txt"))
    }

    @Test
    fun `readResource returns no contents from an empty file`() {
        val fileIo = FileIo()
        val expectedResult = ""
        assertEquals(expectedResult, fileIo.readResource("src/test/kotlin/resources/runtime-empty-file.txt"))
    }

    @Test
    fun `readResource throws an error when file does not exist`() {
        val fileIo = FileIo()
        assertFailsWith<FileNotFoundException> { fileIo.readResource("src/test/kotlin/resources/no-file.csv") }
    }

    @Test
    fun `readFile is able to read from a static file`() {
        val fileIo = FileIo()
        val expectedResult = "Test\nFile\n"
        assertEquals(expectedResult, fileIo.readFile("static-test-file.txt"))
    }

    @Test
    fun `readFile returns no contents from an empty static file`() {
        val fileIo = FileIo()
        val expectedResult = ""
        assertEquals(expectedResult, fileIo.readFile("static-empty-file.txt"))
    }

    @Test
    fun `readFile throws an error when file does not exist`() {
        val fileIo = FileIo()
        assertFailsWith<NullPointerException> { fileIo.readFile("static-no-file.csv") }
    }

    @Test
    fun `clear file returns an empty file`() {
        val fileIo = FileIo()
        val path = "src/test/kotlin/resources/clear-file.txt"
        fileIo.clearFile(path)
        val expectedResult = ""
        assertEquals(expectedResult, fileIo.readResource(path))
    }
}
