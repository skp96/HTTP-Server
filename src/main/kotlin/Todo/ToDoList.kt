package Todo

import Utilities.FileInterface
import Utilities.JsonGenerator
import java.io.FileNotFoundException

class ToDoList(private val filePath: String, private val fileIo: FileInterface, private val jsonGenerator: JsonGenerator) {

    fun addTask(task: Task): String {
        val taskId = calculateId()
        task.setId(taskId)
        val jsonTask = jsonGenerator.resourceToJson(task)
        fileIo.writeResource(filePath, jsonTask)
        return task.body
    }

    fun retrieveList(): List<String> {
        return try {
            val tasks = fileIo.readResource(filePath)
            tasks.split("\n").toList()
        }catch (e: FileNotFoundException) {
            listOf()
        }
    }

    private fun calculateId(): Int {
        val listOfTasks = retrieveList()
        return if (listOfTasks.isEmpty()) {
            1
        } else {
            listOfTasks.size
        }
    }
}
