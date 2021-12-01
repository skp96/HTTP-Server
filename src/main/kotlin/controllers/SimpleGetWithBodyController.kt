package controllers
import response.ResponseBuilder
import httpstatus.HttpStatus

class SimpleGetWithBodyController : Controller {
    private val statusCode = HttpStatus.OK
    private val body = "Hello world"

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode, body)
    }
}
