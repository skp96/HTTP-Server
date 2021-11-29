import controllers.*
import router.Router
import kotlin.test.*

class RouterTest {
    @Test fun testExpectAddRouteMemberToAddSimpleGetToRoutes() {
        val badRequestController = BadRequestController()
        val notFoundController = NotFoundController()
        val routes = Router(badRequestController, notFoundController)
        val simpleGetController: Controller = SimpleGetController()
        routes.addRoute("GET", "/simple_get", simpleGetController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("GET" to mutableMapOf("/simple_get" to simpleGetController))
        assertEquals(expectation, routes.routes)
    }

    @Test fun testExpectAddRouteMemberToAddSimpleGetBodyToRoutes() {
        val badRequestController = BadRequestController()
        val notFoundController = NotFoundController()
        val routes = Router(badRequestController, notFoundController)
        val simpleGetWithBodyController: Controller = SimpleGetWithBodyController()
        routes.addRoute("GET", "/simple_get_with_body", simpleGetWithBodyController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("GET" to mutableMapOf("/simple_get_with_body" to simpleGetWithBodyController))
        assertEquals(expectation, routes.routes)
    }

    @Test fun testGetControllerToReturnAControllerFromRoutes() {
        val badRequestController = BadRequestController()
        val notFoundController = NotFoundController()
        val routes = Router(badRequestController, notFoundController)
        routes.addRoute("GET", "/simple_get_with_body", SimpleGetWithBodyController())
        val controller = routes.getController("GET", "/simple_get")
        assertIs<Controller>(controller)
    }

}