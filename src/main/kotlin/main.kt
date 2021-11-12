import java.net.ServerSocket

fun main() {
    var serverSocket: ServerSocket = ServerSocket(5000)

    Server(serverSocket).start()
}