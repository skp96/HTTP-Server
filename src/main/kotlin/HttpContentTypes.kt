package contenttype

enum class HttpContentTypes(val type: String) {
    HTML("text/html"), JSON("application/json");
    val parameter = ";charset=utf-8"
}
