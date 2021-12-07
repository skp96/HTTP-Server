package Actions

import httpstatus.HttpStatus
import mocks.HTTPResponseBuilderMock
import kotlin.test.*

class GetHTMLResponseActionTest {
    @Test
    fun `act method sets the correct properties on ResponseBuilder`() {
        val action = GetHTMLResponseAction()
        val responseBuilder = HTTPResponseBuilderMock()
        action.act(responseBuilder)
        assertEquals(HttpStatus.OK, responseBuilder.httpStatusCode)
        assertEquals(mapOf("Content-Type" to "text/html;charset=utf-8"), responseBuilder.httpHeaders)
        assertEquals("<html><body><p>HTML Response</p></body></html>", responseBuilder.httpBody)
    }
}
