package request
class Request(val httpMethod: String,
              val route: String,
              val httpHeaders: MutableMap<String, String> = mutableMapOf(),
              val body: String = "")
