package HttpServerErrors
import response.ResponseBuilder

interface HttpServerError {
    fun handleError(responseBuilder: ResponseBuilder): String
}
