package request

class RequestParser {
    fun parse(clientRequest: String): Request {
        val requestComponents: List<String> = clientRequest.split("\r\n").toList()
        val requestLine: List<String> = requestComponents[0].split(" ").toList()
        val httpMethod = requestLine[0]
        val route = requestLine[1]
        return Request(httpMethod, route)
    }
}
