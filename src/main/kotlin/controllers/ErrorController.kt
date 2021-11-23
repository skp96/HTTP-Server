package controllers

class ErrorController : Controller {
    val protocol = "HTTP/1.1"
    val statusCode = "400"
    val statusMessage = "Bad Request"
    val clrf = "\r\n"

    override fun generate_response(): String {
        val response = protocol + " " + statusCode + " " + statusMessage + clrf
        return response
    }
}