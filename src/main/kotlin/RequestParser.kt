package request

class RequestParser {
    private lateinit var requestComponents: List<String>
    private lateinit var httpMethod: String
    private lateinit var route: String

    fun parse(clientRequest: String): Request {
        requestComponents = clientRequest.split("\r\n").toList()
        extractRequestLine()
        val httpHeaders = extractHeaders()
        if (containsBody()) {
            val body = requestComponents.last()
            return Request(httpMethod, route, httpHeaders, body)
        }

        return Request(httpMethod, route, httpHeaders)
    }

    private fun extractRequestLine() {
        val requestLine: List<String> = requestComponents[0].split(" ").toList()
        httpMethod = requestLine[0]
        route = requestLine[1]
    }

    private fun containsBody(): Boolean {
        for (component in requestComponents) {
            if (component.contains("Content-Length")) {
                return true
            }
        }
        return false
    }

    private fun extractHeaders(): MutableMap<String, String> {
        val httpHeaders: MutableMap<String, String> = mutableMapOf()
        for (component_idx in 0 until requestComponents.size - 1) {
            val component = requestComponents[component_idx]
            if (component.contains(":")) {
                val separatorIdx = component.indexOf(":")
                val headerKey = component.substring(0, separatorIdx)
                val headerValue = component.substring(separatorIdx + 1, component.length)
                httpHeaders[headerKey.trim()] = headerValue.trim()
            }
        }
        return httpHeaders
    }
}
