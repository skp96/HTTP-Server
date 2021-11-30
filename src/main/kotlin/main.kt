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
    router.addRoute("POST", "/echo_body", SimplePostController())

    println("Server is running on port ${serverSocket.localPort}")
    Server(serverSocket, parser, router).start()
}
