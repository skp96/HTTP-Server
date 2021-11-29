package controllers
import response.ResponseBuilder

class SimpleGetWithBodyController : Controller {
    private val statusCode = 200
    private val body = "Hello world"

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode, body)
    }
}
