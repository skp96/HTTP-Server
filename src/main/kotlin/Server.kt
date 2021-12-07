import router.Router
import request.RequestParser
import response.ResponseBuilder
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class Server(private val serverSocket: ServerSocket,
             private val parser: RequestParser,
             private val responseBuilder: ResponseBuilder,
             private val router: Router) {

    private lateinit var socket: Socket
    private lateinit var reader: BufferedReader
    private lateinit var writer: PrintWriter


    fun start() {
        while(true) {
            acceptConnection()
            try {
                val clientRequest = readRequest()

                val request = parser.parse(clientRequest)
                val action = router.routeRequest(request)
                val response = action.act(responseBuilder)

                writeResponse(response)
            } catch (e: Exception) {
                println("Something went wrong: ${e.message}")
            } finally {
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
            clientRequest += reader.read().toChar()
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
