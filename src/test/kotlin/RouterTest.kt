import controllers.*
import request.Request
import router.Router
import kotlin.test.*

class RouterTest {
    private val badRequestController = BadRequestController()
    private val notFoundController = NotFoundController()
    private val router = Router(badRequestController, notFoundController)

    @Test
    fun `expect addRoute member to add GET method to the simple_get route`() {
        val simpleGetController: Controller = SimpleGetController()
        router.addRoute("GET", "/simple_get", simpleGetController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("/simple_get" to mutableMapOf("GET" to simpleGetController))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect addRoute member to add GET method to simple_get_with_body route`() {
        val simpleGetWithBodyController: Controller = SimpleGetWithBodyController()
        router.addRoute("GET", "/simple_get_with_body", simpleGetWithBodyController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("/simple_get_with_body" to mutableMapOf("GET" to simpleGetWithBodyController))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return a controller from simple_get_with_body route`() {
        val simpleGetWithBodyController: Controller = SimpleGetWithBodyController()
        router.addRoute("GET", "/simple_get_with_body", simpleGetWithBodyController)
        val request = Request("GET", "/simple_get_with_body")
        val controller = router.routeRequest(request)
        assertIs<SimpleGetWithBodyController>(controller)
    }

    @Test
    fun `expect addRoute member to add HEAD method to simple_get route`() {
        val simpleGetController: Controller = SimpleGetController()
        router.addRoute("HEAD", "/simple_get", simpleGetController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("/simple_get" to mutableMapOf("HEAD" to simpleGetController))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return a controller from simple_get route when http method is HEAD`() {
        val simpleGetController: Controller = SimpleGetController()
        router.addRoute("HEAD", "/simple_get", simpleGetController)
        val request = Request("HEAD", "/simple_get")
        val controller = router.routeRequest(request)
        assertIs<SimpleGetController>(controller)
    }

    @Test
    fun `expect addRoute member to add HEAD method to head_request route`() {
        val simpleGetController: Controller = SimpleGetController()
        router.addRoute("HEAD", "/head_request", simpleGetController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("/head_request" to mutableMapOf("HEAD" to simpleGetController))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return a controller from head_request route when http method is HEAD`() {
        val simpleGetController: Controller = SimpleGetController()
        router.addRoute("HEAD", "/head_request", simpleGetController)
        val request = Request("HEAD", "/head_request")
        val controller = router.routeRequest(request)
        assertIs<SimpleGetController>(controller)
    }

    @Test
    fun `expect addRoute member to add POST method to echo_body route`() {
        val simplePostController: Controller = SimplePostController()
        router.addRoute("POST", "/echo_body", simplePostController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("/echo_body" to mutableMapOf("POST" to simplePostController))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return a controller from echo_body when http method is POST`() {
        val simplePostController: Controller = SimplePostController()
        router.addRoute("POST", "/echo_body", simplePostController)
        val request = Request("POST", "/echo_body")
        val controller = router.routeRequest(request)
        assertIs<SimplePostController>(controller)
    }

}
