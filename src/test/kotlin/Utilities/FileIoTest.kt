import Utilities.FileIo
import kotlin.test.*

class FileIoTest {
    @Test
    fun `readFile is able to read from a file`() {
        val fileIo = FileIo()
        val expectedResult = "Test\nFile\n"
        assertEquals(expectedResult, fileIo.readResource("test-file.txt"))
    }

    @Test
    fun `readFile returns no contents from an empty file`() {
        val fileIo = FileIo()
        val expectedResult = ""
        assertEquals(expectedResult, fileIo.readResource("empty-file.txt"))
    }

    @Test
    fun `readFile throws an error when file does not exist`() {
        val fileIo = FileIo()
        assertFailsWith<NullPointerException> { fileIo.readResource("no-file.csv") }
    }
}
