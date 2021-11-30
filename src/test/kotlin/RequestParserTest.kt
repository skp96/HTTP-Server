import kotlin.test.*
import request.RequestParser
import request.Request

class RequestParserTest {
    @Test
    fun `when parsing a simple_get from client then return Request`() {
        val parser = RequestParser()
        val clientRequest = "GET /simple_get HTTP/1.1\r\n" + "Connection: close\r\n" + "Host: 127.0.0.1:5000\r\n" +
                "User-Agent: http.rb/4.3.0\r\n" + "Content-Length: 0"
        assertIs<Request>(parser.parse(clientRequest))
    }

    @Test
    fun `when parsing a simple_get from client then Request contains http method and route`() {
        val parser = RequestParser()
        val clientRequest = "GET /simple_get HTTP/1.1\r\n" + "Connection: close\r\n" + "Host: 127.0.0.1:5000\r\n" +
                "User-Agent: http.rb/4.3.0\r\n" + "Content-Length: 0"

        val request = parser.parse(clientRequest)

        assertEquals(request.httpMethod, "GET")
        assertEquals(request.route, "/simple_get")
    }

    @Test
    fun `when parsing a simple_get_with_body from client then return Request`() {
        val parser = RequestParser()
        val clientRequest = "GET /simple_get_with_body HTTP/1.1\r\n" + "Connection: close\r\n" + "Host: 127.0.0.1:5000\r\n" +
                "User-Agent: http.rb/4.3.0\r\n" + "Content-Length: 0"
        assertIs<Request>(parser.parse(clientRequest))
    }

    @Test
    fun `when parsing a simple_get_with_body from client then Request contains http method and route`() {
        val parser = RequestParser()
        val clientRequest = "GET /simple_get_with_body HTTP/1.1\r\n" + "Connection: close\r\n" + "Host: 127.0.0.1:5000\r\n" +
                "User-Agent: http.rb/4.3.0\r\n" + "Content-Length: 0"

        val response = parser.parse(clientRequest)

        assertEquals(response.httpMethod, "GET")
        assertEquals(response.route, "/simple_get_with_body")
    }
}
