import router.Router
import request.RequestParser
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class Server(private val socket: ServerSocket,
             private val parser: RequestParser,
             private val router: Router) {

    fun start() {
        while(true) {
            val connection = socket.accept() // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. Returns a new socket
            try {
                // Read from request inputstream
                val clientRequest = readRequest(connection)

                val request = parser.parse(clientRequest)
                val httpMethod = request.httpMethod
                val route = request.route

                val controller = router.getController(httpMethod, route)
                val response = controller.action()
                // Write to outputstream
                writeResponse(connection, response.build())
            } catch (e: Exception) {
                //Logging error
                println("Something went wrong: ${e.message}")
            } finally {
                //Close connection
                connection.close()
            }

        }
    }

    private fun readRequest(socketConnection: Socket): String {
        val reader = BufferedReader(InputStreamReader(socketConnection.getInputStream()))
        var clientRequest = ""

        while (reader.ready()) {
            clientRequest += reader.readLine()
        }
        return clientRequest
    }

    private fun writeResponse(socketConnection: Socket, response: String) {
        val writer = PrintWriter(socketConnection.getOutputStream(), true)
        writer.println(response)
    }
}