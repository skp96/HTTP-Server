package response

class ResponseBuilder(private val statusCode: Int,
                      private val body: String = "",
                      private val headers: Map<String, List<String>> = mapOf()
                      ) {
    private val protocol = "HTTP/1.1"
    private val crlf = "\r\n"

    fun build(): String {
        val responseLine = generateResponseLine()
        var responseHeaders = if (headers.isNotEmpty()) generateHeaders() else ""
        var responseBody = if (body.isNotEmpty()) setContentLength() + "$crlf$body" else ""
        return responseLine + responseHeaders + responseBody
    }

    private fun generateResponseLine(): String {
        val statusMessage = findStatusMessage()
        return "$protocol $statusCode $statusMessage$crlf"
    }

    private fun findStatusMessage(): String =
        when(statusCode) {
            200 -> "OK"
            400 -> "Bad Request"
            404 -> "Not Found"
            else -> "500"
        }

    private fun setContentLength() = "Content-Length: ${body.length}$crlf"

    private fun generateHeaders(): String {
        var httpHeaders = ""
        for (header in headers) {
            httpHeaders += "${header.key}: ${header.value.joinToString()}$crlf"
        }
        return httpHeaders
    }
}
