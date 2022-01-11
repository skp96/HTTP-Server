package Errors

import httpstatus.HttpStatus
import response.ResponseBuilder

class BadRequestError: Error {
    override fun handleError(responseBuilder: ResponseBuilder): String {
        responseBuilder.setStatusCode(HttpStatus.BadRequest)
        return responseBuilder.build()
    }
}
