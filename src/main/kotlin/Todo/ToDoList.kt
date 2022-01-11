package Todo

import Utilities.FileInterface
import Utilities.JsonGenerator
import java.io.FileNotFoundException

class ToDoList(private val filePath: String, private val fileIo: FileInterface, private val jsonGenerator: JsonGenerator) {

    fun addTask(requestBody: String): Boolean {
        lateinit var taskData: String
        try{
            taskData = retrieveTaskData(requestBody)
        }catch (e: Exception) {
            return false
        }
        val taskId = calculateId()
        val task = Task(taskId, taskData)
        val jsonTask = jsonGenerator.resourceToJson(task)
        fileIo.writeResource(filePath, jsonTask)
        return true
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

    private fun retrieveTaskData(jsonString: String): String {
        val obj = jsonGenerator.resourceFromJson(jsonString, mutableMapOf<String, String>()::class)
        return obj.getValue("task")
    }
}
