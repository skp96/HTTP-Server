package Actions

import Todo.ToDo
import contenttype.HttpContentTypes
import httpstatus.HttpStatus
import response.ResponseBuilder

class CreateToDoAction(private val toDo: ToDo): Action {
    private val headers = mapOf("Content-Type" to (HttpContentTypes.JSON.type + HttpContentTypes.JSON.parameter))
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        val responseBody = toDo.createToDo(requestBody)
        responseBuilder.setBody(responseBody)
        responseBuilder.setHeaders(headers)
        responseBuilder.setStatusCode(HttpStatus.Created)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
