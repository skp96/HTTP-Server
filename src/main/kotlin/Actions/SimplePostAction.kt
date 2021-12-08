package Actions
import response.HttpResponseBuilder
import httpstatus.HttpStatus
import response.ResponseBuilder

class SimplePostAction : Action {
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        responseBuilder.setBody(requestBody)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
