package controllers
import response.ResponseBuilder
import httpstatus.HttpStatus

class SimpleGetWithBodyController : Controller {
    private val statusCode = HttpStatus.OK
    private val responseBody = "Hello world"
    private lateinit var requestBody: String

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode, responseBody)
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
