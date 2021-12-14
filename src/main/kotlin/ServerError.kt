import httpstatus.HttpStatus
import response.ResponseBuilder

class ServerError {
    fun handleError(responseBuilder: ResponseBuilder): String {
        responseBuilder.setStatusCode(HttpStatus.ServerError)
        return responseBuilder.build()
    }
}
