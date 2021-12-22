package Actions

import Todo.ToDo
import Todo.ToDoList
import Utilities.JsonGenerator
import httpstatus.HttpStatus
import mocks.FileIoMock
import mocks.HTTPResponseBuilderMock
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
        createToDoAction.act(responseBuilder)

        val expectedResponseBody = """{"task":"a new task"}"""

        assertEquals(HttpStatus.Created, responseBuilder.httpStatusCode)
        assertEquals(mapOf("Content-Type" to "application/json;charset=utf-8"), responseBuilder.httpHeaders)
        assertEquals(expectedResponseBody, responseBuilder.httpBody)
    }

}
