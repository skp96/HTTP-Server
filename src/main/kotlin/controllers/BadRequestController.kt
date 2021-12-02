package controllers
import response.ResponseBuilder
import httpstatus.HttpStatus

class BadRequestController : Controller {
    private val statusCode = HttpStatus.BadRequest

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode)
    }
}
