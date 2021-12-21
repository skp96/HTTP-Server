import Utilities.FileIo
import java.io.FileNotFoundException
import kotlin.test.*

class FileIoTest {
    @Test
    fun `readResource is able to read from a file`() {
        val fileIo = FileIo()
        val expectedResult = "Test\nFile\n"
        assertEquals(expectedResult, fileIo.readResource("src/test/resources/test-file.txt"))
    }

    @Test
    fun `readResource returns no contents from an empty file`() {
        val fileIo = FileIo()
        val expectedResult = ""
        assertEquals(expectedResult, fileIo.readResource("src/test/resources/empty-file.txt"))
    }

    @Test
    fun `readResource throws an error when file does not exist`() {
        val fileIo = FileIo()
        assertFailsWith<FileNotFoundException> { fileIo.readResource("src/test/resources/no-file.csv") }
    }

    @Test
    fun `writeResource is able to write to a file`() {
        val fileIo = FileIo()
        fileIo.writeResource("src/test/kotlin/Todo/test-file-2.txt", "Hello world")

        val expectedResult = "Hello world"
        assertEquals(expectedResult, fileIo.readResource("src/test/kotlin/Todo/test-file-2.txt"))
    }
}
