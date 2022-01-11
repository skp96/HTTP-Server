package Actions

import Utilities.FileIo
import httpstatus.HttpStatus
import mocks.HTTPResponseBuilderMock
import request.Request
import kotlin.test.*

internal class GetHtmlHealthCheckActionTest {
    @Test
    fun `act method sets the correct properties on ResponseBuilder`() {
        val fileIo = FileIo()
        val action = GetHtmlHealthCheckAction(fileIo)
        val responseBuilder = HTTPResponseBuilderMock()
        val request = Request("GET", "/health-check.html")
        action.act(responseBuilder, request)

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
