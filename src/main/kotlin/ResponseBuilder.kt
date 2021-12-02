package response
import httpstatus.HttpStatus

class ResponseBuilder(private val statusCode: HttpStatus,
                      private val body: String = "",
                      private val headers: Map<String, String> = mapOf()
                      ) {
    private val protocol = "HTTP/1.1"
    private val crlf = "\r\n"

    fun build(): String {
        val statusLine = generateStatusLine()
        val responseHeaders = generateHeaders()
        val responseBody = if (body.isNotEmpty()) setContentLength() + "$crlf$body" else ""
        return statusLine + responseHeaders + responseBody
    }

    private fun generateStatusLine(): String {
        return "$protocol ${statusCode.value} ${statusCode.message}$crlf"
    }

    private fun setContentLength() = "Content-Length: ${body.length}$crlf"

    private fun generateHeaders(): String {
        var httpHeaders = ""
        for (header in headers) {
            httpHeaders += "${header.key}: ${header.value}$crlf"
        }
        return httpHeaders
    }
}
