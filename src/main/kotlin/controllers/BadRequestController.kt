package controllers
import response.ResponseBuilder

class BadRequestController : Controller {
    private val statusCode = "400"

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode)
    }
}