package controllers
import response.ResponseBuilder
import httpstatus.HttpStatus

class BadRequestController : Controller {
    private val statusCode = HttpStatus.BadRequest
    private lateinit var requestBody: String
    private lateinit var method: String

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode)
    }

    override fun setBody(body: String) {
        requestBody = body
    }

    override fun setHttpMethod(httpMethod: String) {
        method = httpMethod
    }
}
