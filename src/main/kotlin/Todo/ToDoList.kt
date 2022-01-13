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

    fun updateTask(id: Int, requestBody: String): Boolean {
        lateinit var taskData: String
        return try {
            taskData = retrieveTaskData(requestBody)
            val listOfTasks = retrieveList().toMutableList()
            val taskToUpdate = listOfTasks[id - 1]
            taskToUpdate.setTaskBody(taskData)
            fileIo.clearFile(filePath)
            for (task in listOfTasks) {
                val taskToJson = jsonGenerator.resourceToJson(task)
                fileIo.writeResource(filePath, taskToJson)
            }
            true
        }catch (e: Exception) {
            false
        }
    }

    fun retrieveList(): List<Task> {
        return try {
            val tasksOnFile = fileIo.readResource(filePath)
            val listOfTasks = tasksOnFile.split("\n").toList().dropLast(1)
            listOfTasks.map { jsonGenerator.resourceFromJson(it, Task::class) }
        }catch (e: FileNotFoundException) {
            listOf()
        }
    }

    private fun calculateId(): Int {
        val listOfTasks = retrieveList()
        return if (listOfTasks.isEmpty()) {
            1
        } else {
            listOfTasks.size + 1
        }
    }

    private fun retrieveTaskData(jsonString: String): String {
        val obj = jsonGenerator.resourceFromJson(jsonString, mutableMapOf<String, String>()::class)
        return obj.getValue("task")
    }
}
