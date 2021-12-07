package Actions
import response.HttpResponseBuilder
import httpstatus.HttpStatus
import response.ResponseBuilder

class MethodNotAllowedAction(private val allowedMethods: MutableSet<String>) : Action {
    private val statusCode = HttpStatus.MethodNotAllowed
    private lateinit var requestBody: String
    private lateinit var headers: Map<String, String>

    override fun act(responseBuilder: ResponseBuilder): String {
        generateAllowHeader()
        responseBuilder.setStatusCode(statusCode)
        responseBuilder.setHeaders(headers)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }

    private fun generateAllowHeader() {
        val headerValues = mutableListOf<String>()

        for (methods in allowedMethods) {
            headerValues += methods
        }
        headers = mapOf("Allow" to headerValues.joinToString())
    }
}
