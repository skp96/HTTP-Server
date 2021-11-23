import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.CacheResponse
import java.net.ServerSocket
import java.net.Socket

class Server(private val socket: ServerSocket) {

    fun start() {
        while(true) {
            val connection = socket.accept() // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. Returns a new socket
            try {
                // Read from request inputstream
                val clientRequest = readRequest(connection)
                // Parseing request
                val request = parseRequest(clientRequest)
                // Request
                val httpMethod = request[0]
                val route = request[1]
                // Response from router
                val response = router(httpMethod, route)
                // Write to outputstream
                writeResponse(connection, response)
            } catch (e: Exception) {
                //Logging error
                println("Something went wrong: ${e.message}")
            } finally {
                //Close connection
                connection.close()
            }

        }

    }

    private fun readRequest(socketConnection: Socket): MutableList<String> {
        val reader = BufferedReader(InputStreamReader(socketConnection.getInputStream()))
        val clientRequest: MutableList<String> = mutableListOf()

        while (reader.ready()) {
            clientRequest += reader.readLine()
        }
        return clientRequest
    }

    private fun writeResponse(socketConnection: Socket, response: String) {
        val writer = PrintWriter(socketConnection.getOutputStream(), true)
        writer.println(response)
    }

    private fun parseRequest(clientRequest: MutableList<String>): List<String> {
        val request = clientRequest[0].split(" ").toList()
        return request
    }

    private fun buildRoutes(): MutableMap<String, MutableMap<String, String>> {
        val routes: MutableMap<String, MutableMap<String, String>> = mutableMapOf()
        val getRoute = routes.getOrPut("GET") { mutableMapOf() }
        getRoute["/simple_get"] = simpleGetController()
        getRoute["/simple_get_with_body"] = simpleGetBodyController()

        return routes
    }

    private fun router(httpMethod: String, route: String): String {
        val routes = buildRoutes()

        val getRoutes = routes.get(httpMethod)
        if (getRoutes == null) {
            var response = methodNotAllowed()
            return response
        }
        val response = getRoutes[route]
        if (response == null) {
            return resourceNotFound()
        }
        return response
    }

    private fun simpleGetController(): String {
        return "HTTP/1.1 200 OK\r\n"
    }

    private fun simpleGetBodyController(): String {
        return "HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Length: 11\r\n" + "\r\nHello world"
    }

    private fun methodNotAllowed(): String {
        return "HTTP/1.1 405 METHOD NOT ALLOWED\r\n"
    }

    private fun resourceNotFound(): String {
        return "HTTP/1.1 404 NOT FOUND\r\n"
    }

}