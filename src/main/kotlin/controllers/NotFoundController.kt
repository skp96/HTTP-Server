package controllers
import response.ResponseBuilder
import httpstatus.HttpStatus

class NotFoundController : Controller {
    val statusCode = HttpStatus.NotFound

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode)
    }
}
