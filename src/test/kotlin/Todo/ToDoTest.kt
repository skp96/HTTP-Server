package Todo

import Utilities.FileInterface
import Utilities.JsonGenerator
import mocks.FileIoMock
import kotlin.test.*

class ToDoTest {
    @Test
    fun `expect createToDo to return the created task`() {
        val fileIo = FileIoMock()
        val jsonGenerator = JsonGenerator()
        val filePath = "src/test/kotlin/resources/test-task-list.txt"
        val toDoList = ToDoList(filePath, fileIo, jsonGenerator)
        val toDo = ToDo(toDoList)
        val requestBody = """{"task":"a new task"}"""

        val result = toDo.createToDo(requestBody)
        assertEquals(requestBody, result)
    }
}
