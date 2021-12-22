@file:JvmName("Main")
import Actions.*
import HttpServerErrors.InternalServerError
import Todo.ToDo
import Todo.ToDoList
import Utilities.FileIo
import Utilities.JsonGenerator
import java.net.ServerSocket
import request.RequestParser
import response.HttpResponseBuilder
import router.Router

fun main() {
    val serverSocket = ServerSocket(5000)

    val parser = RequestParser()
    val responseBuilder = HttpResponseBuilder()
    val jsonGenerator = JsonGenerator()
    val serverError = InternalServerError()
    val fileIo = FileIo()
    val filepath = "src/main/kotlin/resources/test-task-list.txt"
    val todoList = ToDoList(filepath, fileIo, jsonGenerator)
    val todo = ToDo(todoList)

    val router = Router()
    router.addRoute("GET", "/simple_get", SimpleGetAction())
    router.addRoute("GET", "/simple_get_with_body", SimpleGetWithBodyAction())
    router.addRoute("HEAD", "/simple_get", SimpleGetAction())
    router.addRoute("HEAD", "/head_request", HeadRequestAction())
    router.addRoute("POST", "/echo_body", SimplePostAction())
    router.addRoute("OPTIONS", "/method_options", MethodOptionsAction())
    router.addRoute("OPTIONS", "/method_options2", MethodOptions2Action())
    router.addRoute("GET", "/redirect", RedirectAction())
    router.addRoute("OPTIONS", "/head_request", HeadRequestAction())
    router.addRoute("GET", "/html_response", GetHTMLResponseAction())
    router.addRoute("GET", "/json_response", GetJsonResponseAction(jsonGenerator))
    router.addRoute("GET", "/health-check.html", GetHtmlHealthCheckAction(fileIo))
    router.addRoute("GET", "/xml_response", GetXmlResponseAction())
    router.addRoute("POST", "/todo", CreateToDoAction(todo))

    println("Server is running on port ${serverSocket.localPort}")
    Server(serverSocket, parser, responseBuilder, router, serverError).start()
}
