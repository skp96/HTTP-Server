import kotlin.test.*
import response.ResponseBuilder
import httpstatus.HttpStatus

class ResponseBuilderTest {
    @Test
    fun `given a simple_get request build a response`() {
        val responseBuilder = ResponseBuilder(HttpStatus.OK)
        val expectation = "HTTP/1.1 200 OK\r\n"
        assertEquals(expectation, responseBuilder.build())
    }

    @Test
    fun `given a simple_get_with_body request build a response`() {
        val responseBuilder = ResponseBuilder(HttpStatus.OK, "Hello world")
        val expectation = "HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello world"
        assertEquals(expectation, responseBuilder.build())
    }

    @Test
    fun `given method_options build response`() {
        val headers: Map<String, String> = mapOf("Allow" to "GET, HEAD, OPTIONS")
        val responseBuilder = ResponseBuilder(HttpStatus.OK, headers = headers)
        val expectation = "HTTP/1.1 200 OK\r\nAllow: GET, HEAD, OPTIONS\r\n"
        assertEquals(expectation, responseBuilder.build())
    }

    @Test
    fun `given method_options2 build response`() {
        val headers: Map<String, String> = mapOf("Allow" to "GET, HEAD, OPTIONS, PUT, POST")
        val responseBuilder = ResponseBuilder(HttpStatus.OK, headers = headers)
        val expectation = "HTTP/1.1 200 OK\r\nAllow: GET, HEAD, OPTIONS, PUT, POST\r\n"
        assertEquals(expectation, responseBuilder.build())
    }

    @Test
    fun `given redirect build response`() {
        val headers: Map<String, String> = mapOf("Location" to "http://127.0.0.1:5000/simple_get")
        val responseBuilder = ResponseBuilder(HttpStatus.MovedPermanently, headers = headers)
        val expectation = "HTTP/1.1 301 Moved Permanently\r\nLocation: http://127.0.0.1:5000/simple_get\r\n"
        assertEquals(expectation, responseBuilder.build())
    }
}
