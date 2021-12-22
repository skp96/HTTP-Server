package Todo

class ToDo(private val toDoList: ToDoList) {

    fun createToDo(requestBody: String): String {
        val task = Task(requestBody)
        return toDoList.addTask(task)
    }
}
