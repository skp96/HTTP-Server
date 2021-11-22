@file:JvmName("Main")
import java.net.ServerSocket

fun main() {
    var serverSocket: ServerSocket = ServerSocket(5000)
    println("Server is running on port ${serverSocket.localPort}")
    Server(serverSocket).start()
}