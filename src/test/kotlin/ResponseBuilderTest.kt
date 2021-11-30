import kotlin.test.*
import response.ResponseBuilder

class ResponseBuilderTest {
    @Test
    fun `given a simple_get request build a response`() {
        val responseBuilder = ResponseBuilder(200)
        val expectation = "HTTP/1.1 200 OK\r\n"
        assertEquals(expectation, responseBuilder.build())
    }

    @Test
    fun `given a simple_get_with_body request build a response`() {
        val responseBuilder = ResponseBuilder(200, "Hello world")
        val expectation = "HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello world"
        assertEquals(expectation, responseBuilder.build())
    }

    @Test
    fun `given a echo_body request build a response`() {
        val responseBuilder = ResponseBuilder(200, "some body")
        val expectation = "HTTP/1.1 200 OK\r\nContent-Length: 9\r\n\r\nsome body"
        assertEquals(expectation, responseBuilder.build())
    }
}
