package Actions
import response.ResponseBuilder
import httpstatus.HttpStatus

class SimpleGetAction : Action {
    private val statusCode = HttpStatus.OK
    private lateinit var requestBody: String

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode)
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
