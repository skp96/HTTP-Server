package router
import Actions.Action
import Actions.MethodNotAllowedAction
import Actions.NotFoundAction
import request.Request

class Router() {

    val routes: MutableMap<String, MutableMap<String, Action>> = mutableMapOf()

    fun addRoute(httpMethod: String, route: String, action: Action) {
        val mapMethod = routes.getOrPut(route) { mutableMapOf()}
        mapMethod[httpMethod] = action
    }

    fun routeRequest(request: Request): Action {
        val httpMethod = request.httpMethod
        val requestPath = request.route // todo/1
        val setOfPaths = routes.keys
        val path = locatePath(requestPath, setOfPaths)
        val resourceRoutes = routes.get(path) ?: return NotFoundAction()
        val controller = resourceRoutes.get(httpMethod) ?: return MethodNotAllowedAction(resourceRoutes.keys)
        controller.setBody(request.body)
        return controller
    }

    private fun locatePath(requestPath: String, setOfPaths: MutableSet<String>): String {
        for (path in setOfPaths) {
            if (requestPath.matches(path.toRegex())) {
                return path
            }
        }
        return "null"
    }
}
