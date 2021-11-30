package controllers
import response.ResponseBuilder

class BadRequestController : Controller {
    private val statusCode = 400
    private lateinit var requestBody: String

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode)
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
