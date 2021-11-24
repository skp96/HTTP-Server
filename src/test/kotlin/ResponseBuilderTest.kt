import kotlin.test.*
import response.ResponseBuilder

class ResponseBuilderTest {
    @Test fun testBuildsAResponseForSimpleGet() {
        val responseBuilder = ResponseBuilder("200")
        val expectation = "HTTP/1.1 200 OK\r\n"
        assertEquals(expectation, responseBuilder.build())
    }

    @Test fun testBuildsResponseForSimpelGetWithBody() {
        val responseBuilder = ResponseBuilder("200", "Hello world")
        val expectation = "HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello world"
        assertEquals(expectation, responseBuilder.build())
    }
}