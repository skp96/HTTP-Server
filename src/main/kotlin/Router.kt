package router
import controllers.Controller
import controllers.MethodNotFoundController
import controllers.NotFoundController
import request.Request

class Router() {

    val routes: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf()

    fun addRoute(httpMethod: String, route: String, controller: Controller) {
        val mapMethod = routes.getOrPut(route) { mutableMapOf()}
        mapMethod[httpMethod] = controller
    }

    fun routeRequest(request: Request): Controller {
        val httpMethod = request.httpMethod
        val route = request.route
        val resourceRoutes = routes.get(route) ?: return NotFoundController()
        val controller = resourceRoutes.get(httpMethod) ?: return MethodNotFoundController(resourceRoutes.keys)
        controller.setBody(request.body)
        return controller
    }
}
