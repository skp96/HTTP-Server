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
    private val fileIo = FileIoMock()
    private val jsonGenerator = JsonGenerator()
    private val filePath = "src/test/kotlin/resources/test-task-list.txt"
    private val toDoList = ToDoList(filePath, fileIo, jsonGenerator)
    private val toDo = ToDo(toDoList)
    private val createToDoAction = CreateToDoAction(toDo)
    private val responseBuilder = HTTPResponseBuilderMock()

    @Test
    fun `act method sets the correct properties on ResponseBuilder given valid request`() {
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

    @Test
    fun `given request for unsupported media type act method sets 415 error`() {
        createToDoAction.setBody("<task>a new task</task>")
        val httpHeaders = mutableMapOf("Content-Type" to "text/xml",
            "Connection" to "close",
            "Host" to "127.0.0.1:5000",
            "User-Agent" to "http.rb/4.3.0",
            "Content-Length" to "23"
        )
        val request = Request("POST", "/todo", httpHeaders, "<task>a new task</task>")
        createToDoAction.act(responseBuilder, request)

        assertEquals(HttpStatus.UnsupportedMediaType, responseBuilder.httpStatusCode)
    }

    @Test
    fun `given request with invalid values act method sets 400 error`() {
        createToDoAction.setBody("a new task")
        val httpHeaders = mutableMapOf("Content-Type" to "application/x-www-form-urlencoded",
            "Connection" to "close",
            "Host" to "127.0.0.1:5000",
            "User-Agent" to "http.rb/4.3.0",
            "Content-Length" to "10"
        )
        val request = Request("POST", "/todo", httpHeaders, "a new task")
        createToDoAction.act(responseBuilder, request)

        assertEquals(HttpStatus.BadRequest, responseBuilder.httpStatusCode)
    }

}
