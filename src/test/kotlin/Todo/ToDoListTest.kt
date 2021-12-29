package Todo

import Utilities.JsonGenerator
import mocks.FileIoMock
import kotlin.test.*

class ToDoListTest {
    private  val fileIo = FileIoMock()
    private  val jsonGenerator = JsonGenerator()
    private val filePath = "src/test/kotlin/resources/test-task-list.txt"
    private  val toDoList = ToDoList(filePath, fileIo, jsonGenerator)

    @Test
    fun `expect addTask to add task to file`() {
        val requestBody = """{"task": "test body"}"""
        val result = toDoList.addTask(requestBody)
        val list = fileIo.readResource(filePath)
        val expectedTask = """{"id":1,"body":"test body"}"""
        assertEquals(true, list.contains(expectedTask))
        assertEquals(true, result)

    }

    @Test
    fun `expect retrieveList to return a List of tasks`() {
        val task1 = """{"task": "test body1"}"""
        val task2 = """{"task": "test body2"}"""

        toDoList.addTask(task1)
        toDoList.addTask(task2)

        val result = toDoList.retrieveList()
        val expectedResult = listOf("""{"id":1,"body":"test body1"}""", """{"id":2,"body":"test body2"}""", "")
        assertEquals(expectedResult, result)
    }
}
