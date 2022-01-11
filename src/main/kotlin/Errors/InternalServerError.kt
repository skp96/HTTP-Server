package Errors
import httpstatus.HttpStatus
import response.ResponseBuilder

class InternalServerError : Error {
    override fun handleError(responseBuilder: ResponseBuilder): String {
        responseBuilder.setStatusCode(HttpStatus.ServerError)
        return responseBuilder.build()
    }
}
