package Todo
import kotlin.test.*

class TaskTest {
    @Test
    fun `expect setId to populate id instance variable with value`() {
        val task = Task("test body")
        task.setId(1)
        assertEquals(1, task.id)
    }
}
