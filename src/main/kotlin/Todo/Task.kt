package Todo

class Task(var body: String) {
    var id: Int? = null

    fun setId(id: Int) = apply { this.id = id }
}
