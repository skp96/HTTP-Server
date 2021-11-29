import controllers.*
import router.Router
import kotlin.test.*

class RouterTest {
    private val badRequestController = BadRequestController()
    private val notFoundController = NotFoundController()
    private val router = Router(badRequestController, notFoundController)

    @Test fun testExpectAddRouteMemberToAddGetMethodToSimpleGetRoute() {
        val simpleGetController: Controller = SimpleGetController()
        router.addRoute("GET", "/simple_get", simpleGetController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("/simple_get" to mutableMapOf("GET" to simpleGetController))
        assertEquals(expectation, router.routes)
    }

    @Test fun testExpectAddRouteMemberToAddGetMethodToSimpleGetBodyRoute() {
        val simpleGetWithBodyController: Controller = SimpleGetWithBodyController()
        router.addRoute("GET", "/simple_get_with_body", simpleGetWithBodyController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("/simple_get_with_body" to mutableMapOf("GET" to simpleGetWithBodyController))
        assertEquals(expectation, router.routes)
    }

    @Test fun testGetControllerToReturnAControllerFromRoutes() {
        val simpleGetWithBodyController: Controller = SimpleGetWithBodyController()
        router.addRoute("GET", "/simple_get_with_body", simpleGetWithBodyController)
        val controller = router.getController("GET", "/simple_get")
        assertIs<Controller>(controller)
    }

    @Test fun testExpectAddRouteMemberToAddHeadMethodToSimpleGetRoute() {
        val simpleGetController: Controller = SimpleGetController()
        router.addRoute("HEAD", "/simple_get", simpleGetController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("/simple_get" to mutableMapOf("HEAD" to simpleGetController))
        assertEquals(expectation, router.routes)
    }

    @Test fun testReturnSimpleGetControllerWhenHeadRequestDirectedToSimpleGetRoute() {
        val simpleGetController: Controller = SimpleGetController()
        router.addRoute("HEAD", "/simple_get", simpleGetController)
        val controller = router.getController("HEAD", "/simple_get")
        assertIs<Controller>(controller)
    }

    @Test fun testExpectAddRouteMemberToAddHeadMethodToHeadRequestRoute() {
        val simpleGetController: Controller = SimpleGetController()
        router.addRoute("HEAD", "/head_request", simpleGetController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("/head_request" to mutableMapOf("HEAD" to simpleGetController))
        assertEquals(expectation, router.routes)
    }

    @Test fun testReturnSimpleGetControllerWhenHeadRequestDirectedToHeadRequestRoute() {
        val simpleGetController: Controller = SimpleGetController()
        router.addRoute("HEAD", "/head_request", simpleGetController)
        val controller = router.getController("HEAD", "/head_request")
        assertIs<Controller>(controller)
    }

}
