package controllers

import response.ResponseBuilder

class MethodOptions2Controller : Controller {
    private val statusCode = 200
    private val headers: Map<String, List<String>> = mapOf("Allow" to listOf("GET", "HEAD", "OPTIONS", "PUT", "POST"))

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode, headers = headers)
    }
}
