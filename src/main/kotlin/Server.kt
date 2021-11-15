import java.net.ServerSocket
import java.nio.charset.Charset
import java.util.*

class Server(socket: ServerSocket) {
    private val socket = socket

    fun start() {
        println("Server is running on port ${socket.localPort}")
        val connection = socket.accept()
        val writer = connection.getOutputStream()
        val reader = Scanner(connection.getInputStream())

        try {
            val input = reader.nextLine()
            println(input)
            writer.write(("HTTP/1.1 " + "200 OK\n").toByteArray(Charsets.UTF_8))
            writer.write("HTTP Server Coming Soon!\n".toByteArray(Charsets.UTF_8))
        }catch (ex: Exception) {
            writer.write("Something went wrong, try again later".toByteArray())
        } finally {
            connection.close()
        }
    }
}