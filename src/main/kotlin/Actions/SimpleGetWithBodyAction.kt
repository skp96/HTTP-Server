package Actions
import response.HttpResponseBuilder
import httpstatus.HttpStatus
import response.ResponseBuilder

class SimpleGetWithBodyAction : Action {
    private val responseBody = "Hello world"
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        responseBuilder.setBody(responseBody)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
