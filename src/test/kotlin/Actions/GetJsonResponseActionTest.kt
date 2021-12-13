package Actions

import utilities.JsonGenerator
import httpstatus.HttpStatus
import mocks.HTTPResponseBuilderMock
import org.json.JSONObject
import kotlin.test.*

class GetJsonResponseActionTest {
    @Test
    fun `act method sets the correct properties on ResponseBuilder`() {
        val jsonGenerator = JsonGenerator()
        val action = GetJsonResponseAction(jsonGenerator)
        val responseBuilder = HTTPResponseBuilderMock()

        action.act(responseBuilder)

        val expectedJsonBody = JSONObject()
        expectedJsonBody.put("key1", "value1")
        expectedJsonBody.put("key2", "value2")

        assertEquals(HttpStatus.OK, responseBuilder.httpStatusCode)
        assertEquals(mapOf("Content-Type" to "application/json;charset=utf-8"), responseBuilder.httpHeaders)
        assertEquals(expectedJsonBody.toString(), responseBuilder.httpBody)
    }
}
