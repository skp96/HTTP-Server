package controllers

import httpstatus.HttpStatus
import response.ResponseBuilder

class HeadRequestController : Controller {
    private var statusCode = HttpStatus.OK
    private val headers: Map<String, String> = mapOf("Allow" to "HEAD, OPTIONS")
    private lateinit var requestBody: String
    private lateinit var method: String

    override fun action(): ResponseBuilder {
        if (checkForInvalidMethod()) {
            statusCode = HttpStatus.NotAllowed
            return ResponseBuilder(statusCode, headers = headers)
        }
        return ResponseBuilder(statusCode)
    }

    override fun setBody(body: String) {
        requestBody = body
    }

    override fun setHttpMethod(httpMethod: String) {
        method = httpMethod
    }

    private fun checkForInvalidMethod(): Boolean {
        val allowedMethods = headers["Allow"]
        if (allowedMethods != null) {
            if (!allowedMethods.contains(method)) {
                return true
            }
        }
        return false
    }
}
