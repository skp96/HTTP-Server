package Actions

import httpstatus.HttpStatus
import request.Request
import response.ResponseBuilder

class HeadRequestAction : Action {
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder, request: Request): String {
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
