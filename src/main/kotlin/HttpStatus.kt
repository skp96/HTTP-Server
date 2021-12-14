package httpstatus

enum class HttpStatus(val value: Int) {
    OK(200), MovedPermanently(301), NotFound(404),
    MethodNotAllowed(405), ServerError(500);
    val message: String
        get() = when(this) {
            OK -> "OK"
            MovedPermanently -> "Moved Permanently"
            NotFound -> "Not Found"
            MethodNotAllowed -> "Method Not Allowed"
            ServerError -> "Internal Server Error"
        }
}
