import contenttype.HttpContentTypes
import kotlin.test.*
import response.HttpResponseBuilder
import httpstatus.HttpStatus
import org.json.JSONObject

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

    @Test
    fun `given http_response build response`() {
        val httpResponseBuilder = HttpResponseBuilder()
        httpResponseBuilder.setStatusCode(HttpStatus.OK)
        httpResponseBuilder.setHeaders(mapOf("Content-Type" to "text/html;charset=utf-8"))
        httpResponseBuilder.setBody("<html><body><p>HTML Response</p></body></html>")
        val expectation = "HTTP/1.1 200 OK\r\nContent-Type: text/html;charset=utf-8\r\nContent-Length: 46\r\n\r\n<html><body><p>HTML Response</p></body></html>"
        assertEquals(expectation, httpResponseBuilder.build())
    }

    @Test
    fun `given json_response build response`() {
        val httpResponseBuilder = HttpResponseBuilder()
        httpResponseBuilder.setHeaders(mapOf("Content-Type" to (HttpContentTypes.JSON.type + HttpContentTypes.JSON.parameter)))
        val expectedJsonBody = JSONObject()
        expectedJsonBody.put("key1", "value1")
        expectedJsonBody.put("key2", "value2")
        httpResponseBuilder.setBody(expectedJsonBody.toString())
        val expectation = "HTTP/1.1 200 OK\r\nContent-Type: application/json;charset=utf-8\r\nContent-Length: 33\r\n\r\n{\"key1\":\"value1\",\"key2\":\"value2\"}"
        assertEquals(expectation, httpResponseBuilder.build())
    }

    @Test
    fun `given health-check build response`() {
        val httpResponseBuilder = HttpResponseBuilder()
        httpResponseBuilder.setHeaders(mapOf("Content-Type" to (HttpContentTypes.HTML.type + HttpContentTypes.HTML.parameter)))
        val responseBody = "<!doctype>\n" +
                "<html lang=\"en-US\">\n" +
                "<head>\n" +
                "  <title>TODO List</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<main>\n" +
                "  <strong>Status:</strong> pass\n" +
                "</main>\n" +
                "</body>\n" +
                "</html>\n"
        httpResponseBuilder.setBody(responseBody)
        val expectedResult = "HTTP/1.1 200 OK\r\nContent-Type: text/html;charset=utf-8\r\nContent-Length: 144\r\n\r\n${responseBody}"
        assertEquals(expectedResult, httpResponseBuilder.build())
    }

    @Test
    fun `given a server error build response`() {
        val httpResponseBuilder = HttpResponseBuilder()
        httpResponseBuilder.setStatusCode(HttpStatus.ServerError)

        val expectedResult = "HTTP/1.1 500 Internal Server Error\r\n"
        assertEquals(expectedResult, httpResponseBuilder.build())
    }

    @Test
    fun `given xml_response build response`() {
        val httpResponseBuilder = HttpResponseBuilder()
        httpResponseBuilder.setHeaders(mapOf("Content-Type" to (HttpContentTypes.XML.type + HttpContentTypes.XML.parameter)))
        val responseBody = "<note><body>XML Response</body></note>"
        httpResponseBuilder.setBody(responseBody)
        val expectedResult = "HTTP/1.1 200 OK\r\nContent-Type: application/xml;charset=utf-8\r\nContent-Length: 38\r\n\r\n${responseBody}"
        assertEquals(expectedResult, httpResponseBuilder.build())
    }
}
