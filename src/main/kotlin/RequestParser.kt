package request

class RequestParser {
    private lateinit var requestComponents: List<String>
    private lateinit var httpMethod: String
    private lateinit var route: String
    private lateinit var body: String

    fun parse(clientRequest: String): Request {
        requestComponents = clientRequest.split("\r\n").toList()
        extractRequestLine()

        if (containsBody()) {
            body = requestComponents.last()
        }

        return Request(httpMethod, route, body)
    }

    private fun extractRequestLine() {
        val requestLine: List<String> = requestComponents[0].split(" ").toList()
        httpMethod = requestLine[0]
        route = requestLine[1]
    }

    private fun containsBody(): Boolean {
        for (components in requestComponents) {
            if (components.contains("Content-Length")) {
                return true
            }
        }
        return false
    }
}
