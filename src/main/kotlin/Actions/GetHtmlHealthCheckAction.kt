package Actions

import contenttype.HttpContentTypes
import response.ResponseBuilder
import java.io.File

class GetHtmlHealthCheckAction: Action {
    private val headers = mapOf("Content-Type" to (HttpContentTypes.HTML.type + HttpContentTypes.HTML.parameter))
    private val responseBody = readText()
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        responseBuilder.setHeaders(headers)
        responseBuilder.setBody(responseBody)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }

    fun readText(): String {
        val file = File("src/assets/health_check.html")
        val contents = file.readText(Charsets.UTF_8)
        return contents
    }
}
