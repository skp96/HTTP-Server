package mocks

import httpstatus.HttpStatus
import response.ResponseBuilder

class HTTPResponseBuilderMock : ResponseBuilder {
    var httpStatusCode = HttpStatus.OK
    var httpBody = ""
    var httpHeaders: Map<String, String> = mapOf()

    override fun build(): String {
        return ""
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


}
