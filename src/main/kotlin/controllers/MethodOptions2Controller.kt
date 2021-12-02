package controllers
import response.ResponseBuilder
import httpstatus.HttpStatus

class MethodOptions2Controller : Controller {
    private val statusCode = HttpStatus.OK
    private val headers: Map<String, String> = mapOf("Allow" to "GET, HEAD, OPTIONS, PUT, POST")

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode, headers = headers)
    }
}
