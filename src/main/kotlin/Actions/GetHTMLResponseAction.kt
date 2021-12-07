package Actions

import httpstatus.HttpStatus
import response.ResponseBuilder

class GetHTMLResponseAction : Action {
    private val statusCode = HttpStatus.OK
    private val headers = mapOf("Content-Type" to "text/html;charset=utf-8")
    private val responseBody = "<html><body><p>HTML Response</p></body></html>"
    private lateinit var requestBody: String


    override fun act(responseBuilder: ResponseBuilder): String {
        responseBuilder.setStatusCode(statusCode)
        responseBuilder.setHeaders(headers)
        responseBuilder.setBody(responseBody)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
