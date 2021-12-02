package httpstatus

enum class HttpStatus(val value: Int) {
    OK(200), BadRequest(400), NotFound(404), MovedPermanently(301), InternalServerError(500);
    val message: String
        get() = when(this) {
            OK -> "OK"
            BadRequest -> "Bad Request"
            NotFound -> "Not Found"
            MovedPermanently -> "Moved Permanently"
            InternalServerError -> "Internal Server Error"
        }
}
