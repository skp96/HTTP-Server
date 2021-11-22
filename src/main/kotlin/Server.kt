import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

class Server(private val socket: ServerSocket) {

    fun start() {
        while(true) {
            val connection = socket.accept()
            val writer = PrintWriter(connection.getOutputStream(), true)
            val reader = BufferedReader(InputStreamReader(connection.getInputStream()))
            try {
                val clientRequest: MutableList<String> = mutableListOf()
                while(reader.ready()) {
                    clientRequest += reader.readLine()
                }

                val requestLine: List<String> = clientRequest[0].split(" ").toList()
                val httpMethod = requestLine[0]
                val resource = requestLine[1]
                println(httpMethod)
                if (httpMethod == "GET" && (resource == "/simple_get" || resource == "/")) {
                    writer.println("HTTP/1.1 200 OK\r\n")
                }

                if (httpMethod == "GET" && resource == "/simple_get_with_body") {
                    writer.println("HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Length: 11\r\n" + "\r\nHello world")
                }

                connection.close()
            } catch (e: Exception) {
                println("Something went wrong: ${e.message}")
            }

        }

    }
}