package controllers

class SimpleGetWithBodyController : Controller {
    val protocol = "HTTP/1.1"
    val statusCode = "200"
    val statusMessage = "OK"
    val clrf = "\r\n"
    val body = "Hello world"

    override fun generate_response(): String {
        val response = protocol + " " + statusCode + " " + statusMessage + clrf + clrf + body
        return response
    }
}