package Actions

import response.HttpResponseBuilder
import httpstatus.HttpStatus
import request.Request
import response.ResponseBuilder

class RedirectAction : Action {
    private val statusCode = HttpStatus.MovedPermanently
    private val headers: Map<String, String> = mapOf("Location" to "http://127.0.0.1:5000/simple_get")
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder, request: Request): String {
        responseBuilder.setStatusCode(statusCode)
        responseBuilder.setHeaders(headers)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
