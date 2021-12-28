package Actions

import Todo.ToDo
import Todo.ToDoList
import Utilities.JsonGenerator
import httpstatus.HttpStatus
import mocks.FileIoMock
import mocks.HTTPResponseBuilderMock
import request.Request
import kotlin.test.*

class CreateToDoActionTest {
    @Test
    fun `act method sets the correct properties on ResponseBuilder`() {
        val fileIo = FileIoMock()
        val jsonGenerator = JsonGenerator()
        val filePath = "src/test/kotlin/resources/test-task-list.txt"
        val toDoList = ToDoList(filePath, fileIo, jsonGenerator)
        val toDo = ToDo(toDoList)
        val createToDoAction = CreateToDoAction(toDo)
        val responseBuilder = HTTPResponseBuilderMock()
        createToDoAction.setBody("""{"task":"a new task"}""")
        val httpHeaders = mutableMapOf("Content-Type" to "application/json",
            "Connection" to "close",
            "Host" to "127.0.0.1:5000",
            "User-Agent" to "http.rb/4.3.0",
            "Content-Length" to "21"
        )
        val request = Request("POST", "/todo", httpHeaders, """{"task":"a new task"}""")
        createToDoAction.act(responseBuilder, request)

        val expectedResponseBody = """{"task":"a new task"}"""

        assertEquals(HttpStatus.Created, responseBuilder.httpStatusCode)
        assertEquals(mapOf("Content-Type" to "application/json;charset=utf-8"), responseBuilder.httpHeaders)
        assertEquals(expectedResponseBody, responseBuilder.httpBody)
    }

}
