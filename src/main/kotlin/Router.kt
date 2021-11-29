package router
import controllers.Controller

class Router(private val badRequestController: Controller,
             private val notFoundController: Controller) {

    val routes: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf()

    fun addRoute(httpMethod: String, route: String, controller: Controller) {
        val mapMethod = routes.getOrPut(route) { mutableMapOf()}
        mapMethod[httpMethod] = controller
    }

    fun getController(httpMethod: String, route: String): Controller {
        val resourceRoutes = routes.get(route) ?: return notFoundController
        return resourceRoutes.get(httpMethod) ?: return badRequestController
    }
}
