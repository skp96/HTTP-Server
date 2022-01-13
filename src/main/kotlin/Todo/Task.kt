package Todo

data class Task(val id: Int, var body: String) {

    fun setTaskBody(requestBody: String) = apply {this.body = requestBody}
}
