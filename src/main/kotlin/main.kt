@file:JvmName("Main")
import controllers.*
import java.net.ServerSocket
import request.RequestParser
import router.Router

fun main() {
    val serverSocket = ServerSocket(5000)

    val parser = RequestParser()

    val router = Router()
    router.addRoute("GET", "/simple_get", SimpleGetController())
    router.addRoute("GET", "/simple_get_with_body", SimpleGetWithBodyController())
    router.addRoute("HEAD", "/simple_get", SimpleGetController())
    router.addRoute("HEAD", "/head_request", HeadRequestController())
    router.addRoute("POST", "/echo_body", SimplePostController())
    router.addRoute("OPTIONS", "/method_options", MethodOptionsController())
    router.addRoute("OPTIONS", "/method_options2", MethodOptions2Controller())
    router.addRoute("GET", "/redirect", RedirectController())
    router.addRoute("OPTIONS", "/head_request", HeadRequestController())

    println("Server is running on port ${serverSocket.localPort}")
    Server(serverSocket, parser, router).start()
}
