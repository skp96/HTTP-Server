@file:JvmName("Main")
import java.net.ServerSocket
import request.RequestParser
import router.Router
import controllers.BadRequestController
import controllers.NotFoundController
import controllers.SimpleGetController
import controllers.SimpleGetWithBodyController

fun main() {
    val serverSocket = ServerSocket(5000)
    val parser = RequestParser()
    val badRequestController = BadRequestController()
    val notFoundController = NotFoundController()
    val router = Router(badRequestController, notFoundController)
    router.addRoute("GET", "/simple_get", SimpleGetController())
    router.addRoute("GET", "/simple_get_with_body", SimpleGetWithBodyController())

    println("Server is running on port ${serverSocket.localPort}")
    Server(serverSocket, parser, router).start()
}