package router
import controllers.Controller
import request.Request

class Router(private val badRequestController: Controller,
             private val notFoundController: Controller) {

    val routes: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf()

    fun addRoute(httpMethod: String, route: String, controller: Controller) {
        val mapMethod = routes.getOrPut(route) { mutableMapOf()}
        mapMethod[httpMethod] = controller
    }

    fun routeRequest(request: Request): Controller {
        val httpMethod = request.httpMethod
        val route = request.route
        val resourceRoutes = routes.get(route) ?: return notFoundController
        val controller = resourceRoutes.get(httpMethod) ?: return badRequestController
        controller.setBody(request.body)
        controller.setHttpMethod(request.httpMethod)
        return controller
    }
}
