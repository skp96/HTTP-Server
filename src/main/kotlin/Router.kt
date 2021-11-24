package router
import controllers.Controller

class Router(private val badRequestController: Controller,
             private val notFoundController: Controller) {

    val routes: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf()

    fun addRoute(httpMethod: String, route: String, controller: Controller) {
        val mapMethod = routes.getOrPut(httpMethod) { mutableMapOf()}
        mapMethod[route] = controller
    }

    fun getController(httpMethod: String, route: String): Controller {
        val httpMethodRoutes = routes.get(httpMethod) ?: return badRequestController
        return httpMethodRoutes.get(route) ?: return notFoundController
    }
}