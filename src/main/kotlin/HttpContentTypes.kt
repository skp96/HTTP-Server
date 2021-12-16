package contenttype

enum class HttpContentTypes(val type: String) {
    HTML("text/html"), JSON("application/json"), XML("application/xml");
    val parameter = ";charset=utf-8"
}
