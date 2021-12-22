package Todo

import Utilities.FileInterface
import Utilities.FileIo
import Utilities.JsonGenerator
import java.io.FileNotFoundException

class ToDoList(private val filePath: String, private val fileIo: FileInterface, private val jsonGenerator: JsonGenerator) {

    fun addTask(task: Task) {
        val taskId = calculateId()
        task.setId(taskId)
        val jsonTask = jsonGenerator.resourceToJson(task)
        fileIo.writeResource(filePath, jsonTask)
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
        if (listOfTasks.isEmpty()) {
            return 1
        } else {
            return listOfTasks.size
        }
    }
}
