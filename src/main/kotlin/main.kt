@file:JvmName("Main")
import controllers.*
import java.net.ServerSocket
import request.RequestParser
import router.Router

fun main() {
    val serverSocket = ServerSocket(5000)

    val parser = RequestParser()

    val badRequestController = BadRequestController()
    val notFoundController = NotFoundController()
    val router = Router(badRequestController, notFoundController)
    router.addRoute("GET", "/simple_get", SimpleGetController())
    router.addRoute("GET", "/simple_get_with_body", SimpleGetWithBodyController())
    router.addRoute("HEAD", "/simple_get", SimpleGetController())
    router.addRoute("HEAD", "/head_request", SimpleGetController())
    router.addRoute("OPTIONS", "/method_options", MethodOptionsController())
    router.addRoute("OPTIONS", "/method_options2", MethodOptions2Controller())
    router.addRoute("GET", "/redirect", RedirectController())

    println("Server is running on port ${serverSocket.localPort}")
    Server(serverSocket, parser, router).start()
}
