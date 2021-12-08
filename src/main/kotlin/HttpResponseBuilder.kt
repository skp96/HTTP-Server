package response
import httpstatus.HttpStatus

class HttpResponseBuilder : ResponseBuilder {
    private val protocol = "HTTP/1.1"
    private val crlf = "\r\n"
    private var httpStatusCode = HttpStatus.OK
    private var httpBody = ""
    private var httpHeaders: Map<String, String> = mapOf()

    override fun build(): String {
        val statusLine = generateStatusLine()
        val responseHeaders = generateHeaders()
        val responseBody = if (httpBody.isNotEmpty()) setContentLength() + "$crlf$httpBody" else ""
        reset()
        return statusLine + responseHeaders + responseBody
    }

    override fun setStatusCode(statusCode: HttpStatus) {
        httpStatusCode = statusCode
    }

    override fun setBody(body: String) {
        httpBody = body
    }

    override fun setHeaders(headers: Map<String, String>) {
        httpHeaders = headers
    }

    override fun reset() {
        httpStatusCode = HttpStatus.OK
        httpBody = ""
        httpHeaders = mapOf()
    }

    private fun generateStatusLine(): String {
        return "$protocol ${httpStatusCode.value} ${httpStatusCode.message}$crlf"
    }

    private fun setContentLength() = "Content-Length: ${this.httpBody.length}$crlf"

    private fun generateHeaders(): String {
        var headers = ""
        for (header in httpHeaders) {
            headers += "${header.key}: ${header.value}$crlf"
        }
        return headers
    }
}
