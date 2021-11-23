package controllers

class SimpleGetController : Controller {
    val protocol = "HTTP/1.1"
    val statusCode = "200"
    val statusMessage = "OK"
    val clrf = "\r\n"

    override fun generate_response(): String {
        val response = protocol + " " + statusCode + " " + statusMessage + clrf
        return response
    }
}