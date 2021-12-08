package Actions

import contenttype.HttpContentTypes
import response.ResponseBuilder

class GetHTMLResponseAction : Action {
    private val headers = mapOf("Content-Type" to HttpContentTypes.HTML.type)
    private val responseBody = "<html><body><p>HTML Response</p></body></html>"
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
