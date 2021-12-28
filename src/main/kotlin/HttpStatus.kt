package httpstatus

enum class HttpStatus(val value: Int) {
    OK(200),
    Created(201),
    MovedPermanently(301),
    BadRequest(400),
    NotFound(404),
    MethodNotAllowed(405),
    UnsupportedMediaType(415),
    ServerError(500);
    val message: String
        get() = when(this) {
            OK -> "OK"
            Created -> "Created"
            MovedPermanently -> "Moved Permanently"
            BadRequest -> "Bad Request"
            NotFound -> "Not Found"
            MethodNotAllowed -> "Method Not Allowed"
            UnsupportedMediaType -> "Unsupported Media Type"
            ServerError -> "Internal Server Error"
        }
}
