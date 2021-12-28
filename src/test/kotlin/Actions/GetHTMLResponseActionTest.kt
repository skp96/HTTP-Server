package Actions

import httpstatus.HttpStatus
import mocks.HTTPResponseBuilderMock
import request.Request
import kotlin.test.*

class GetHTMLResponseActionTest {
    @Test
    fun `act method sets the correct properties on ResponseBuilder`() {
        val action = GetHTMLResponseAction()
        val responseBuilder = HTTPResponseBuilderMock()
        val request = Request("GET", "/html_response")
        action.act(responseBuilder, request)
        assertEquals(HttpStatus.OK, responseBuilder.httpStatusCode)
        assertEquals(mapOf("Content-Type" to "text/html;charset=utf-8"), responseBuilder.httpHeaders)
        assertEquals("<html><body><p>HTML Response</p></body></html>", responseBuilder.httpBody)
    }
}
