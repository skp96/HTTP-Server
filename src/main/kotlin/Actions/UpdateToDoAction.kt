package Actions

import Errors.BadRequestError
import Todo.ToDoList
import contenttype.HttpContentTypes
import request.Request
import response.ResponseBuilder

class UpdateToDoAction(private val toDoList: ToDoList): Action {
    private val headers = mapOf("Content-Type" to (HttpContentTypes.JSON.type + HttpContentTypes.JSON.parameter))
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder, request: Request): String {
        val taskId = parseId(request.route)
        val isTaskUpdated = toDoList.updateTask(taskId, requestBody)
        return if (isTaskUpdated) {
            responseBuilder.setBody(requestBody)
            responseBuilder.setHeaders(headers)
            responseBuilder.build()
        } else {
            BadRequestError().handleError(responseBuilder)
        }
    }

    override fun setBody(body: String) {
        requestBody = body
    }

    private fun parseId(route: String): Int {
        val routeParts = route.split("/")
        val idParam = routeParts.last()
        return idParam.toInt()
    }
}
