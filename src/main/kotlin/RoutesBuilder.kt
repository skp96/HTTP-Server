package builder
import controllers.Controller

class RoutesBuilder(val errorController: Controller) {
    val routes: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf()

    fun addRoute(httpMethod: String, route: String, controller: Controller) {
        val mapMethod = routes.getOrPut(httpMethod) { mutableMapOf()}
        mapMethod[route] = controller
    }

    fun getController(httpMethod: String, route: String): Controller {
        val httpMethodRoutes = routes.get(httpMethod)

        if (httpMethodRoutes == null) {
            return errorController
        }
        val controller = httpMethodRoutes[route]
        if (controller == null) {
            return errorController
        }
        return controller
    }
}