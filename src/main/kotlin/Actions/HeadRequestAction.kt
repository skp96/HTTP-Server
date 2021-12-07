package Actions

import httpstatus.HttpStatus
import response.ResponseBuilder

class HeadRequestAction : Action {
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder): String {
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
