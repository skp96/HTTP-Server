@file:JvmName("Main")
import java.net.ServerSocket
import request.RequestParser

fun main() {
    val serverSocket: ServerSocket = ServerSocket(5000)
    val parser = RequestParser()
    println("Server is running on port ${serverSocket.localPort}")
    Server(serverSocket, parser).start()
}