package Errors
import response.ResponseBuilder

interface Error {
    fun handleError(responseBuilder: ResponseBuilder): String
}
