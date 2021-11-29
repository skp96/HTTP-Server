package controllers
import response.ResponseBuilder

class SimpleGetController : Controller {
    private val statusCode = 200

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode)
    }
}
