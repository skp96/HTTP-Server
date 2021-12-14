package Actions
import httpstatus.HttpStatus
import response.ResponseBuilder

class MethodOptions2Action : Action {
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        val headers: Map<String, String> = mapOf("Allow" to "GET, HEAD, OPTIONS, PUT, POST")
        responseBuilder.setHeaders(headers)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
