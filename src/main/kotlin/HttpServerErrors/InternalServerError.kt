package HttpServerErrors
import httpstatus.HttpStatus
import response.ResponseBuilder

class InternalServerError : HttpServerError {
    override fun handleError(responseBuilder: ResponseBuilder): String {
        responseBuilder.setStatusCode(HttpStatus.ServerError)
        return responseBuilder.build()
    }
}
