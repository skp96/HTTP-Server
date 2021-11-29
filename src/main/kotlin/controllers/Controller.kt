package controllers
import response.ResponseBuilder

interface Controller {

    fun action(): ResponseBuilder
}
