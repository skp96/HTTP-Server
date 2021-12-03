package controllers
import response.ResponseBuilder
import httpstatus.HttpStatus

class MethodNotFoundController(private val allowedMethods: MutableSet<String>) : Controller {
    private val statusCode = HttpStatus.MethodNotAllowed
    private lateinit var requestBody: String
    private lateinit var headers: Map<String, String>

    override fun action(): ResponseBuilder {
        generateAllowHeader()
        return ResponseBuilder(statusCode, headers = headers)
    }

    override fun setBody(body: String) {
        requestBody = body
    }

    private fun generateAllowHeader() {
        val headerValues = mutableListOf<String>()

        for (methods in allowedMethods) {
            headerValues += methods
        }
        headers = mapOf("Allow" to headerValues.joinToString())
    }
}
