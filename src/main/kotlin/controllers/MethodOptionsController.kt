package controllers
import response.ResponseBuilder
import httpstatus.HttpStatus

class MethodOptionsController : Controller {
    private val statusCode = HttpStatus.OK
    private val headers: Map<String, String> = mapOf("Allow" to "GET, HEAD, OPTIONS")

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode, headers = headers)
    }
}
