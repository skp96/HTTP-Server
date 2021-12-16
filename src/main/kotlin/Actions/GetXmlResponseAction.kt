package Actions

import contenttype.HttpContentTypes
import response.ResponseBuilder

class GetXmlResponseAction : Action {
    private val headers = mapOf("Content-Type" to (HttpContentTypes.XML.type + HttpContentTypes.XML.parameter))
    private val responseBody = "<note><body>XML Response</body></note>"
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
