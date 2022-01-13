package Actions

import Todo.ToDoList
import Utilities.JsonGenerator
import httpstatus.HttpStatus
import mocks.FileIoMock
import mocks.HTTPResponseBuilderMock
import request.Request
import kotlin.test.*

class UpdateToDoActionTest {
    private val fileIo = FileIoMock()
    private val jsonGenerator = JsonGenerator()
    private val filePath = "src/test/kotlin/resources/test-task-list.txt"
    private val toDoList = ToDoList(filePath, fileIo, jsonGenerator)
    private val updateToDoAction = UpdateToDoAction(toDoList)
    private val responseBuilder = HTTPResponseBuilderMock()

    @Test
    fun `act method sets the correct properties on ResponseBuilder given valid request`() {
        val requestBody = """{"task":"an updated task"}"""
        updateToDoAction.setBody(requestBody)
        val httpHeaders = mutableMapOf("Content-Type" to "application/json",
            "Connection" to "close",
            "Host" to "127.0.0.1:5000",
            "User-Agent" to "http.rb/4.3.0",
            "Content-Length" to "${requestBody.length}"
        )
        val request = Request("PUT", "/todo/1", httpHeaders, requestBody)
        updateToDoAction.act(responseBuilder, request)

        assertEquals(HttpStatus.OK, responseBuilder.httpStatusCode)
        assertEquals(mapOf("Content-Type" to "application/json;charset=utf-8"), responseBuilder.httpHeaders)
        assertEquals(requestBody, responseBuilder.httpBody)
    }
}
