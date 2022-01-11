package Actions
import response.HttpResponseBuilder
import httpstatus.HttpStatus
import request.Request
import response.ResponseBuilder

class SimpleGetWithBodyAction : Action {
    private val responseBody = "Hello world"
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder, request: Request): String {
        responseBuilder.setBody(responseBody)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
