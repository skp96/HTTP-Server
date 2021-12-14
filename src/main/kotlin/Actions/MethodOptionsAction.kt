package Actions
import httpstatus.HttpStatus
import response.ResponseBuilder

class MethodOptionsAction : Action {
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        val headers: Map<String, String> = mapOf("Allow" to "GET, HEAD, OPTIONS")
        responseBuilder.setHeaders(headers)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
