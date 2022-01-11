package Actions
import request.Request
import response.ResponseBuilder

interface Action {
    fun act(responseBuilder: ResponseBuilder, request: Request): String
    fun setBody(body: String)
}
