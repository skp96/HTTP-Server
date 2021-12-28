package Errors

import httpstatus.HttpStatus
import response.ResponseBuilder

class UnsupportedMediaError : Error {
    override fun handleError(responseBuilder: ResponseBuilder): String {
        responseBuilder.setStatusCode(HttpStatus.UnsupportedMediaType)
        return responseBuilder.build()
    }
}
