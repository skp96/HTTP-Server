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
    fun `given method_options build response`() {
        val headers: Map<String, List<String>> = mapOf("Allow" to listOf("GET", "HEAD", "OPTIONS"))
        val responseBuilder = ResponseBuilder(200, headers = headers)
        val expectation = "HTTP/1.1 200 OK\r\nAllow: GET, HEAD, OPTIONS\r\n"
        assertEquals(expectation, responseBuilder.build())
    }

    @Test
    fun `given method_options2 build response`() {
        val headers: Map<String, List<String>> = mapOf("Allow" to listOf("GET", "HEAD", "OPTIONS", "PUT", "POST"))
        val responseBuilder = ResponseBuilder(200, headers = headers)
        val expectation = "HTTP/1.1 200 OK\r\nAllow: GET, HEAD, OPTIONS, PUT, POST\r\n"
        assertEquals(expectation, responseBuilder.build())
    }
}
