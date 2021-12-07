package Actions
import response.HttpResponseBuilder
import httpstatus.HttpStatus
import response.ResponseBuilder

class SimpleGetAction : Action {
    private val statusCode = HttpStatus.OK
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        responseBuilder.setStatusCode(statusCode)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
