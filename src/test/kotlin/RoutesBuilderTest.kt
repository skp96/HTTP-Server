import builder.RoutesBuilder
import controllers.Controller
import controllers.SimpleGetController
import controllers.SimpleGetWithBodyController
import controllers.ErrorController
import kotlin.test.*

class RoutesBuilderTest {
    @Test fun testExpectAddRouteMemberToAddSimpleGetToRoutes() {
        val errorController = ErrorController()
        val routes = RoutesBuilder(errorController)
        val simpleGetController: Controller = SimpleGetController()
        routes.addRoute("GET", "/simple_get", simpleGetController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("GET" to mutableMapOf("/simple_get" to simpleGetController))
        assertEquals(expectation, routes.routes)
    }

    @Test fun testExpectAddRouteMemberToAddSimpleGetBodyToRoutes() {
        val errorController = ErrorController()
        val routes = RoutesBuilder(errorController)
        val simpleGetWithBodyController: Controller = SimpleGetWithBodyController()
        routes.addRoute("GET", "/simple_get_with_body", simpleGetWithBodyController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("GET" to mutableMapOf("/simple_get_with_body" to simpleGetWithBodyController))
        assertEquals(expectation, routes.routes)
    }

    @Test fun testGetControllerToReturnAControllerFromRoutes() {
        val errorController = ErrorController()
        val routes = RoutesBuilder(errorController)
        routes.addRoute("GET", "/simple_get_with_body", SimpleGetWithBodyController())
        val controller = routes.getController("GET", "/simple_get")
        assertIs<Controller>(controller)
    }

}