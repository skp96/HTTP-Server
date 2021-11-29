package response

class ResponseBuilder(private val statusCode: Int, private val body: String = "") {
    private val protocol = "HTTP/1.1"
    private val crlf = "\r\n"

    fun build(): String {
        val responseLine = generateResponseLine()
        if (body.isNotEmpty()) {
            val contentLength = setContentLength()
            return responseLine + "$contentLength$crlf$crlf$body"
        }
        return responseLine
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

    private fun setContentLength() = "Content-Length: ${body.length}"
}
