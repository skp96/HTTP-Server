package Actions

import Utilities.JsonGenerator
import httpstatus.HttpStatus
import mocks.HTTPResponseBuilderMock
import kotlin.test.*

class GetJsonResponseActionTest {
    @Test
    fun `act method sets the correct properties on ResponseBuilder`() {
        val jsonGenerator = JsonGenerator()
        val action = GetJsonResponseAction(jsonGenerator)
        val responseBuilder = HTTPResponseBuilderMock()

        action.act(responseBuilder)

        val expectedJsonBody = """{"key1":"value1","key2":"value2"}"""

        assertEquals(HttpStatus.OK, responseBuilder.httpStatusCode)
        assertEquals(mapOf("Content-Type" to "application/json;charset=utf-8"), responseBuilder.httpHeaders)
        assertEquals(expectedJsonBody, responseBuilder.httpBody)
    }
}
