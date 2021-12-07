package Actions
import httpstatus.HttpStatus
import response.ResponseBuilder

class NotFoundAction : Action {
    val statusCode = HttpStatus.NotFound
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        responseBuilder.setStatusCode(statusCode)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
