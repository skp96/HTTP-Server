package Actions
import response.HttpResponseBuilder
import httpstatus.HttpStatus
import response.ResponseBuilder

class MethodNotAllowedAction(private val allowedMethods: MutableSet<String>) : Action {
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        val headers = generateAllowHeader()
        val statusCode = HttpStatus.MethodNotAllowed
        responseBuilder.setStatusCode(statusCode)
        responseBuilder.setHeaders(headers)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }

    private fun generateAllowHeader(): Map<String, String> {
        val headerValues = mutableListOf<String>()

        for (methods in allowedMethods) {
            headerValues += methods
        }
        return mapOf("Allow" to headerValues.joinToString())
    }
}
