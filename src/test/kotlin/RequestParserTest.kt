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

        assertEquals("GET", request.httpMethod)
        assertEquals("/simple_get" ,request.route)
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

        val request = parser.parse(clientRequest)

        assertEquals("GET", request.httpMethod)
        assertEquals("/simple_get_with_body", request.route)
    }

    @Test
    fun `when parsing a echo_body from client then Request contains http method, route, and body`() {
        val parser = RequestParser()
        val clientRequest = "POST /echo_body HTTP/1.1\r\n" + "Connection: close\r\n" + "Host: 127.0.0.1:5000" +
                "User-Agent: http.rb/4/3.0\r\n" + "Content-Length: 9\r\n" + "\r\nsome body"

        val request = parser.parse(clientRequest)

        assertEquals("POST",request.httpMethod)
        assertEquals("/echo_body", request.route)
        assertEquals("some body", request.body)
    }

    @Test
    fun `when parsing an html_response from client then Request contains http method, route`() {
        val parser = RequestParser()
        val clientRequest = "GET /html_response HTTP/1.1\r\n" + "Connection: close\r\n" + "Host: 127.0.0.1:5000" +
                "User-Agent: http.rb/4.3.0" + "Content-Length: 0"

        val request = parser.parse(clientRequest)

        assertEquals("GET", request.httpMethod)
        assertEquals("/html_response", request.route)
    }

    @Test
    fun `when parsing json_response from client then Request contains http method, route`() {
        val parser = RequestParser()
        val clientRequest = "GET /json_response HTTP/1.1\r\n" + "Connection: close\r\n" + "Host: 127.0.0.1:5000" +
                "User-Agent: http.rb/4.3.0" + "Content-Length: 0"

        val request = parser.parse(clientRequest)

        assertEquals("GET", request.httpMethod)
        assertEquals("/json_response", request.route)
    }

    @Test
    fun `when parsing health-check from client then Request contains http method, route`() {
        val parser = RequestParser()
        val clientRequest = "GET /health-check.html HTTP/1.1\r\n" + "Connection: close\r\n" + "Host: 127.0.0.1:5000" +
                "User-Agent: http.rb/4.3.0" + "Content-Length: 0"

        val request = parser.parse(clientRequest)
        assertEquals("GET", request.httpMethod)
        assertEquals("/health-check.html", request.route)
    }

    @Test
    fun `when parsing xml_response from client then Request contains http method, route`() {
        val parser = RequestParser()
        val clientRequest = "GET /xml_response HTTP/1.1\r\n" + "Connection: close\r\n" + "Host: 127.0.0.1:5000" +
                "User-Agent: http.rb/4.3.0" + "Content-Length: 0"

        val request = parser.parse(clientRequest)
        assertEquals("GET", request.httpMethod)
        assertEquals("/xml_response", request.route)
    }

    @Test
    fun `when parsing todo request from client then Request contains http method, route, and body`() {
        val parser = RequestParser()
        val clientRequest = "POST /todo HTTP/1.1\r\n" + "Content-Type: application/json\r\n" +"Connection: close\r\n" + "Host: 127.0.0.1:5000\r\n" +
                "User-Agent: http.rb/4.3.0\r\n" + "Content-Length: 21\r\n" + "\r\n{\"task\":\"a new task\"}"
        val request = parser.parse(clientRequest)
        val expectedHeaders = mutableMapOf("Content-Type" to "application/json",
            "Connection" to "close",
            "Host" to "127.0.0.1:5000",
            "User-Agent" to "http.rb/4.3.0",
            "Content-Length" to "21"
        )
        assertEquals("POST", request.httpMethod)
        assertEquals("/todo", request.route)
        assertEquals(expectedHeaders, request.httpHeaders)
        assertEquals("{\"task\":\"a new task\"}", request.body)
    }

    @Test
    fun `when parsing todo-1 request from client then Request contains http method, route, and body`() {
        val parser = RequestParser()
        val clientRequest = "PUT /todo/1 HTTP/1.1\r\n" + "Content-Type: application/json\r\n" + "Connection: close\r\n" + "Host: 127.0.0.1:5000\r\n" +
                "User-Agent: http.rb/4.3.0\r\n" + "Content-Length: 26\r\n" + "\r\n{\"task\":\"an updated task\"}"
        val request = parser.parse(clientRequest)
        val expectedHeaders = mutableMapOf("Content-Type" to "application/json",
            "Connection" to "close",
            "Host" to "127.0.0.1:5000",
            "User-Agent" to "http.rb/4.3.0",
            "Content-Length" to "26"
        )
        assertEquals("PUT", request.httpMethod)
        assertEquals("/todo/1", request.route)
        assertEquals(expectedHeaders, request.httpHeaders)
        assertEquals("{\"task\":\"an updated task\"}", request.body)
    }
}
