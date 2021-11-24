package controllers
import response.ResponseBuilder

class NotFoundController : Controller {
    val statusCode = "404"

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode)
    }
}