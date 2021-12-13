import Utilities.FileIo
import kotlin.test.*

class FileIoTest {
    @Test
    fun `readFile is able to read from html file`() {
        val fileIo = FileIo()
        val expectedResult = "<!doctype>\n" +
                "<html lang=\"en-US\">\n" +
                "<head>\n" +
                "    <title>TODO List</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<main>\n" +
                "    <strong>Status:</strong> pass\n" +
                "</main>\n" +
                "</body>\n" +
                "</html>\n"
        assertEquals(expectedResult, fileIo.readResource("test-html.html"))
    }
}
