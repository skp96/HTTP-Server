package controllers
import response.ResponseBuilder

class SimplePostController : Controller {
    private val statusCode = 200
    private lateinit var requestBody: String

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode, requestBody)
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
