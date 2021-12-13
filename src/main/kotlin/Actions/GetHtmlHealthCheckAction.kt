package Actions

import Utilities.FileIo
import contenttype.HttpContentTypes
import response.ResponseBuilder
import java.io.File

class GetHtmlHealthCheckAction(fileIo: FileIo): Action {
    private val headers = mapOf("Content-Type" to (HttpContentTypes.HTML.type + HttpContentTypes.HTML.parameter))
    private val responseBody = fileIo.readResource("health_check.html")
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        responseBuilder.setHeaders(headers)
        responseBuilder.setBody(responseBody)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
