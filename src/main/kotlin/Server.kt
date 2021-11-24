import router.Router
import request.RequestParser
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class Server(private val serverSocket: ServerSocket,
             private val parser: RequestParser,
             private val router: Router) {

    lateinit private var socket: Socket
    lateinit private var reader: BufferedReader
    lateinit private var writer: PrintWriter


    fun start() {
        while(true) {
            acceptConnection()
            try {
                val clientRequest = readRequest()

                val request = parser.parse(clientRequest)
                val httpMethod = request.httpMethod
                val route = request.route

                val response = router.getController(httpMethod, route).action()

                writeResponse(response.build())
            } catch (e: Exception) {
                //Logging error
                println("Something went wrong: ${e.message}")
            } finally {
                //Close connection
                closeConnection()
            }

        }
    }

    fun acceptConnection() {
        socket = serverSocket.accept()
        reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        writer = PrintWriter(socket.getOutputStream(), true)
    }

    fun readRequest(): String {
        var clientRequest = ""

        while (reader.ready()) {
            clientRequest += reader.readLine()
        }
        return clientRequest
    }

    fun writeResponse(response: String) {
        writer.println(response)
    }

    fun closeConnection() {
        reader.close()
        writer.close()
        socket.close()
    }
}