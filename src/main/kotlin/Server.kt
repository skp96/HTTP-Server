import HttpServerErrors.HttpServerError
import router.Router
import request.RequestParser
import response.ResponseBuilder
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class Server(private val serverSocket: ServerSocket,
             private val parser: RequestParser,
             private val responseBuilder: ResponseBuilder,
             private val router: Router,
             private val serverError: HttpServerError) {

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
                val response = action.act(responseBuilder, request)

                writeResponse(response)
            } catch (e: Exception) {
                println("Something went wrong: ${e.message}")
                val response = serverError.handleError(responseBuilder)
                writeResponse(response)
            } finally {
                closeConnection()
            }

        }
    }

    private fun acceptConnection() {
        socket = serverSocket.accept()
        val inputStream = populateInputStream(socket)
        reader = BufferedReader(InputStreamReader(inputStream))
        writer = PrintWriter(socket.getOutputStream(), true)
    }

    private fun readRequest(): String {
        var clientRequest = ""

        while (reader.ready()) {
            clientRequest += reader.read().toChar()
        }
        return clientRequest
    }

    private fun writeResponse(response: String) {
        writer.println(response)
    }

    private fun closeConnection() {
        reader.close()
        writer.close()
        socket.close()
    }

    private fun populateInputStream(socket: Socket): InputStream {
        val stream = socket.getInputStream()
        while(stream.available() == 0) {
            continue
        }
        return stream
    }
}
