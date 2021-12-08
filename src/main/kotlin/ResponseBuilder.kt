package response

import httpstatus.HttpStatus

interface ResponseBuilder {
    fun build(): String
    fun setStatusCode(statusCode: HttpStatus)
    fun setBody(body: String)
    fun setHeaders(headers: Map<String, String>)
    fun reset()
}
