package Actions

import httpstatus.HttpStatus
import mocks.HTTPResponseBuilderMock
import response.HttpResponseBuilder
import kotlin.test.*

internal class GetHtmlHealthCheckActionTest {
    @Test
    fun `readFile is able to read from html file`() {
        val action = GetHtmlHealthCheckAction()
        val expectedResult = "<!doctype>\n" +
                "<html lang=\"en-US\">\n" +
                "<head>\n" +
                "  <title>TODO List</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<main>\n" +
                "  <strong>Status:</strong> pass\n" +
                "</main>\n" +
                "</body>\n" +
                "</html>\n"
        assertEquals(expectedResult, action.readText())
    }

    @Test
    fun `act method sets the correct properties on ResponseBuilder`() {
        val action = GetHtmlHealthCheckAction()
        val responseBuilder = HTTPResponseBuilderMock()

        action.act(responseBuilder)

        val expectedBody = "<!doctype>\n" +
                "<html lang=\"en-US\">\n" +
                "<head>\n" +
                "  <title>TODO List</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<main>\n" +
                "  <strong>Status:</strong> pass\n" +
                "</main>\n" +
                "</body>\n" +
                "</html>\n"

        assertEquals(HttpStatus.OK, responseBuilder.httpStatusCode)
        assertEquals(mapOf("Content-Type" to "text/html;charset=utf-8"), responseBuilder.httpHeaders)
        assertEquals(expectedBody, responseBuilder.httpBody)
    }
}
