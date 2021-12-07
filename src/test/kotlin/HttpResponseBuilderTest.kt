import kotlin.test.*
import response.HttpResponseBuilder
import httpstatus.HttpStatus

class HttpResponseBuilderTest {
    @Test
    fun `given a simple_get request build a response`() {
        val httpResponseBuilder = HttpResponseBuilder()
        httpResponseBuilder.setStatusCode(HttpStatus.OK)
        val expectation = "HTTP/1.1 200 OK\r\n"
        assertEquals(expectation, httpResponseBuilder.build())
    }

    @Test
    fun `given a simple_get_with_body request build a response`() {
        val httpResponseBuilder = HttpResponseBuilder()
        httpResponseBuilder.setStatusCode(HttpStatus.OK)
        httpResponseBuilder.setBody("Hello world")
        val expectation = "HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello world"
        assertEquals(expectation, httpResponseBuilder.build())
    }

    @Test
    fun `given a echo_body request build a response`() {
        val httpResponseBuilder = HttpResponseBuilder()
        httpResponseBuilder.setStatusCode(HttpStatus.OK)
        httpResponseBuilder.setBody("some body")
        val expectation = "HTTP/1.1 200 OK\r\nContent-Length: 9\r\n\r\nsome body"
        assertEquals(expectation, httpResponseBuilder.build())
    }

    @Test
    fun `given method_options build response`() {
        val httpResponseBuilder = HttpResponseBuilder()
        httpResponseBuilder.setStatusCode(HttpStatus.OK)
        httpResponseBuilder.setHeaders(mapOf("Allow" to "GET, HEAD, OPTIONS"))
        val expectation = "HTTP/1.1 200 OK\r\nAllow: GET, HEAD, OPTIONS\r\n"
        assertEquals(expectation, httpResponseBuilder.build())
    }

    @Test
    fun `given method_options2 build response`() {
        val httpResponseBuilder = HttpResponseBuilder()
        httpResponseBuilder.setHeaders(mapOf("Allow" to "GET, HEAD, OPTIONS, PUT, POST"))
        val expectation = "HTTP/1.1 200 OK\r\nAllow: GET, HEAD, OPTIONS, PUT, POST\r\n"
        assertEquals(expectation, httpResponseBuilder.build())
    }

    @Test
    fun `given redirect build response`() {
        val httpResponseBuilder = HttpResponseBuilder()
        httpResponseBuilder.setStatusCode(HttpStatus.MovedPermanently)
        httpResponseBuilder.setHeaders(mapOf("Location" to "http://127.0.0.1:5000/simple_get"))
        val expectation = "HTTP/1.1 301 Moved Permanently\r\nLocation: http://127.0.0.1:5000/simple_get\r\n"
        assertEquals(expectation, httpResponseBuilder.build())
    }

    @Test
    fun `given head_request build response`() {
        val httpResponseBuilder = HttpResponseBuilder()
        httpResponseBuilder.setStatusCode(HttpStatus.MethodNotAllowed)
        httpResponseBuilder.setHeaders(mapOf("Allow" to "HEAD, OPTIONS"))
        val expectation = "HTTP/1.1 405 Method Not Allowed\r\nAllow: HEAD, OPTIONS\r\n"
        assertEquals(expectation, httpResponseBuilder.build())
    }
}
