package Actions

import Utilities.FileIo
import contenttype.HttpContentTypes
import response.ResponseBuilder
import java.io.File

class GetHtmlHealthCheckAction(private val fileIo: FileIo): Action {
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        val headers = mapOf("Content-Type" to (HttpContentTypes.HTML.type + HttpContentTypes.HTML.parameter))
        val responseBody = fileIo.readResource("health_check.html")
        responseBuilder.setHeaders(headers)
        responseBuilder.setBody(responseBody)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
