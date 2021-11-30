package controllers
import response.ResponseBuilder

class MethodOptionsController : Controller {
    private val statusCode = 200
    private val headers: Map<String, List<String>> = mapOf("Allow" to listOf("GET", "HEAD", "OPTIONS"))

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode, headers = headers)
    }
}
