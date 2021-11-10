import java.io.*
import java.lang.Exception
import java.net.ServerSocket

class Server(socket: ServerSocket) {
    val socket = socket

    fun start() {
        println("Server is running on port ${socket.localPort}")
        val connection = socket.accept()
        val output = PrintWriter(connection.getOutputStream(), true)
        val input = BufferedReader(InputStreamReader(connection.inputStream))

        try {
            while (input.readLine() != null) {
                output.println("HTTP Server Coming Soon!")
            }
        }catch (ex: Exception) {
            connection.close()
        }
    }
}