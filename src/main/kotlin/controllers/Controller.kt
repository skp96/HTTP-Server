package controllers
import response.ResponseBuilder

interface Controller {
    fun action(): ResponseBuilder
    fun setBody(body: String)
    fun setHttpMethod(httpMethod: String)
}
