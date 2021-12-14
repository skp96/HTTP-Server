package Actions
import httpstatus.HttpStatus
import response.ResponseBuilder

class NotFoundAction : Action {
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        val statusCode = HttpStatus.NotFound
        responseBuilder.setStatusCode(statusCode)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
