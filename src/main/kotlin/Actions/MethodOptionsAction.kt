package Actions
import httpstatus.HttpStatus
import request.Request
import response.ResponseBuilder

class MethodOptionsAction : Action {
    private val headers: Map<String, String> = mapOf("Allow" to "GET, HEAD, OPTIONS")
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder, request: Request): String {
        responseBuilder.setHeaders(headers)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
