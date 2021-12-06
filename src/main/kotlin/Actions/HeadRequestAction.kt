package Actions

import httpstatus.HttpStatus
import response.ResponseBuilder

class HeadRequestAction : Action {
    private var statusCode = HttpStatus.OK
    private lateinit var requestBody: String

    override fun action(): ResponseBuilder {
        return ResponseBuilder(statusCode)
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
