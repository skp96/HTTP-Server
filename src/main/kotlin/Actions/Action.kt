package Actions
import response.ResponseBuilder

interface Action {
    fun action(): ResponseBuilder
    fun setBody(body: String)
}
