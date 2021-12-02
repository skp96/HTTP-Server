package controllers
import response.ResponseBuilder
import httpstatus.HttpStatus

class MethodOptionsController : Controller {
    private val statusCode = HttpStatus.OK
    private val headers: Map<String, String> = mapOf("Allow" to "GET, HEAD, OPTIONS")
    private lateinit var requestBody: String
    private lateinit var method: String

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode, headers = headers)
    }

    override fun setBody(body: String) {
        requestBody = body
    }

    override fun setHttpMethod(httpMethod: String) {
        method = httpMethod
    }
}
