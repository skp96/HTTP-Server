package Actions
import response.ResponseBuilder

interface Action {
    fun act(responseBuilder: ResponseBuilder): String
    fun setBody(body: String)
}
