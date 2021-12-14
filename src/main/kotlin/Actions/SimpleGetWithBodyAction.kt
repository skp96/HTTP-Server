package Actions
import response.HttpResponseBuilder
import httpstatus.HttpStatus
import response.ResponseBuilder

class SimpleGetWithBodyAction : Action {
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        val responseBody = "Hello world"
        responseBuilder.setBody(responseBody)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
