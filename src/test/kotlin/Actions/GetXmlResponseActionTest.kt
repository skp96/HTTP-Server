package Actions

import httpstatus.HttpStatus
import mocks.HTTPResponseBuilderMock
import request.Request
import kotlin.test.*

class GetXmlResponseActionTest {
    @Test
    fun `act method sets the correct properties on ResponseBuilder`() {
        val action = GetXmlResponseAction()
        val responseBuilder = HTTPResponseBuilderMock()
        val request = Request("GET", "/xml_response")
        action.act(responseBuilder, request)
        assertEquals(HttpStatus.OK, responseBuilder.httpStatusCode)
        assertEquals(mapOf("Content-Type" to "application/xml;charset=utf-8"), responseBuilder.httpHeaders)
        assertEquals("<note><body>XML Response</body></note>", responseBuilder.httpBody)
    }

}
