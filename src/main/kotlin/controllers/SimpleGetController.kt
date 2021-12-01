package controllers
import response.ResponseBuilder
import httpstatus.HttpStatus

class SimpleGetController : Controller {
    private val statusCode = HttpStatus.OK

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode)
    }
}
