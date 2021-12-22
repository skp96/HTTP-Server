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
        val task = Task("test body")
        toDoList.addTask(task)
        val list = fileIo.readResource("src/test/kotlin/resources/test-task-list.txt")
        val expectedTask = """{"body":"test body","id":1}"""
        assertEquals(true, list.contains(expectedTask))
    }

    @Test
    fun `expect retrieveList to return a List of tasks`() {
        val task1 = Task("test body1")
        val task2 = Task("test body2")

        toDoList.addTask(task1)
        toDoList.addTask(task2)

        val result = toDoList.retrieveList()
        val expectedResult = listOf("""{"body":"test body1","id":1}""", """{"body":"test body2","id":2}""", "")
        assertEquals(expectedResult, result)
    }
}
