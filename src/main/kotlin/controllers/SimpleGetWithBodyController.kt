package controllers
import response.ResponseBuilder

class SimpleGetWithBodyController : Controller {
    private val statusCode = 200
    private val responseBody = "Hello world"
    private lateinit var requestBody: String

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode, responseBody)
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
