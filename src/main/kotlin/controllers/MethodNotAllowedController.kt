package controllers

import httpstatus.HttpStatus
import response.ResponseBuilder

class MethodNotAllowedController : Controller {
    private val statusCode = HttpStatus.NotAllowed
    private val headers: Map<String, String> = mapOf("Allow" to "HEAD, OPTIONS")
    private lateinit var requestBody: String

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode, headers = headers)
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
