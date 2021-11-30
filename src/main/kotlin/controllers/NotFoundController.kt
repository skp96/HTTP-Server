package controllers
import response.ResponseBuilder

class NotFoundController : Controller {
    private val statusCode = 404
    private lateinit var requestBody: String

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode)
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
