import controllers.*
import request.Request
import router.Router
import kotlin.test.*

class RouterTest {
    private lateinit var router: Router

    @BeforeTest
    fun init() {
        val badRequestController = BadRequestController()
        val notFoundController = NotFoundController()
        router = Router(badRequestController, notFoundController)
    }

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

    @Test
    fun `expect addRoute member to add OPTIONS method to method_options route`() {
        val methodOptionsController = MethodOptionsController()
        router.addRoute("OPTIONS", "/method_options", methodOptionsController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("/method_options" to mutableMapOf("OPTIONS" to methodOptionsController))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return controller from method_options route when http method is OPTIONS`() {
        router.addRoute("OPTIONS", "/method_options", MethodOptionsController())
        val request = Request("OPTIONS", "/method_options")
        val controller = router.routeRequest(request)
        assertIs<MethodOptionsController>(controller)
    }

    @Test
    fun `expect addRoute member to add OPTIONS method to method_options2 route`() {
        val methodOptions2Controller = MethodOptions2Controller()
        router.addRoute("OPTIONS", "/method_options2", methodOptions2Controller)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("/method_options2" to mutableMapOf("OPTIONS" to methodOptions2Controller))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return controller from method_options2 route when http method is OPTIONS`() {
        router.addRoute("OPTIONS", "/method_options2", MethodOptions2Controller())
        val request = Request("OPTIONS", "/method_options2")
        val controller = router.routeRequest(request)
        assertIs<MethodOptions2Controller>(controller)
    }

    @Test
    fun `expect addRoute member to add GET method to redirect route`() {
        val redirectController = RedirectController()
        router.addRoute("GET", "/redirect", redirectController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("/redirect" to mutableMapOf("GET" to redirectController))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return controller from redirect route when http method is GET`() {
        router.addRoute("GET", "/redirect", RedirectController())
        val request = Request("GET", "/redirect")
        val controller = router.routeRequest(request)
        assertIs<RedirectController>(controller)
    }

    @Test
    fun `expect addRoute member to add GET method to head_request`() {
        val methodNotAllowedController = MethodNotAllowedController()
        router.addRoute("GET", "/head_request", methodNotAllowedController)
        val expectation: MutableMap<String, MutableMap<String, Controller>> = mutableMapOf("/head_request" to mutableMapOf("GET" to methodNotAllowedController))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return controller from head_request route when http method is GET`() {
        router.addRoute("GET", "/head_request", MethodNotAllowedController())
        val request = Request("GET", "/head_request")
        val controller = router.routeRequest(request)
        assertIs<MethodNotAllowedController>(controller)
    }

}
