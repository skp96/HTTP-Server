package controllers
import response.ResponseBuilder
import httpstatus.HttpStatus

class SimpleGetController : Controller {
    private val statusCode = HttpStatus.OK
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
