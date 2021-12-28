package Actions

import Errors.BadRequestError
import Errors.UnsupportedMediaError
import Todo.ToDo
import Utilities.JsonGenerator
import contenttype.HttpContentTypes
import httpstatus.HttpStatus
import request.Request
import response.ResponseBuilder

class CreateToDoAction(private val toDo: ToDo): Action {
    private val headers = mapOf("Content-Type" to (HttpContentTypes.JSON.type + HttpContentTypes.JSON.parameter))
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder, request: Request): String {
        return if (validContentType(request) && validBody(request)) {
            val responseBody = toDo.createToDo(requestBody)
            responseBuilder.setBody(responseBody)
            responseBuilder.setHeaders(headers)
            responseBuilder.setStatusCode(HttpStatus.Created)
            responseBuilder.build()
        } else if (validContentType(request) && !validBody(request)) {
            BadRequestError().handleError(responseBuilder)
        } else {
            UnsupportedMediaError().handleError(responseBuilder)
        }
    }

    override fun setBody(body: String) {
        requestBody = body
    }

    private fun validContentType(request: Request): Boolean {
        val httpHeaders = request.httpHeaders
        if (httpHeaders.containsKey("Content-Type")) {
            return httpHeaders["Content-Type"]!!.contains("application")
        }
        return false
    }

    private fun validBody(request: Request): Boolean {
        val requestBody = request.body
        return requestBody.contains(":") && requestBody.contains("{") && requestBody.contains("}")
    }
}
