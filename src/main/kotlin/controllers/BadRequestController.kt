package controllers
import response.ResponseBuilder
import httpstatus.HttpStatus

class BadRequestController : Controller {
    private val statusCode = HttpStatus.BadRequest
    private lateinit var requestBody: String

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode)
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
