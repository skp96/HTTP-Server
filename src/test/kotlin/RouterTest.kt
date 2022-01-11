import Actions.*
import Todo.ToDoList
import Utilities.JsonGenerator
import mocks.FileIoMock
import request.Request
import router.Router
import kotlin.test.*

class RouterTest {
    private lateinit var router: Router

    @BeforeTest
    fun init() {
        router = Router()
    }

    @Test
    fun `expect addRoute member to add GET method to the simple_get route`() {
        val simpleGetAction: Action = SimpleGetAction()
        router.addRoute("GET", "/simple_get", simpleGetAction)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/simple_get" to mutableMapOf("GET" to simpleGetAction))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect addRoute member to add GET method to simple_get_with_body route`() {
        val simpleGetWithBodyAction: Action = SimpleGetWithBodyAction()
        router.addRoute("GET", "/simple_get_with_body", simpleGetWithBodyAction)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/simple_get_with_body" to mutableMapOf("GET" to simpleGetWithBodyAction))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return SimpleGetWithBodyAction from simple_get_with_body route when http method is GET`() {
        val simpleGetWithBodyAction: Action = SimpleGetWithBodyAction()
        router.addRoute("GET", "/simple_get_with_body", simpleGetWithBodyAction)
        val request = Request("GET", "/simple_get_with_body")
        val action = router.routeRequest(request)
        assertIs<SimpleGetWithBodyAction>(action)
    }

    @Test
    fun `expect addRoute member to add HEAD method to simple_get route`() {
        val simpleGetAction: Action = SimpleGetAction()
        router.addRoute("HEAD", "/simple_get", simpleGetAction)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/simple_get" to mutableMapOf("HEAD" to simpleGetAction))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return a SimpleGetAction from simple_get route when http method is HEAD`() {
        val simpleGetAction: Action = SimpleGetAction()
        router.addRoute("HEAD", "/simple_get", simpleGetAction)
        val request = Request("HEAD", "/simple_get")
        val action = router.routeRequest(request)
        assertIs<SimpleGetAction>(action)
    }

    @Test
    fun `expect addRoute member to add HEAD method to head_request route`() {
        val headRequestAction: Action = HeadRequestAction()
        router.addRoute("HEAD", "/head_request", headRequestAction)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/head_request" to mutableMapOf("HEAD" to headRequestAction))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return a HeadRequestAction from head_request route when http method is HEAD`() {
        val headRequestAction: Action = HeadRequestAction()
        router.addRoute("HEAD", "/head_request", headRequestAction)
        val request = Request("HEAD", "/head_request")
        val action = router.routeRequest(request)
        assertIs<HeadRequestAction>(action)
    }

    @Test
    fun `expect addRoute member to add POST method to echo_body route`() {
        val simplePostAction: Action = SimplePostAction()
        router.addRoute("POST", "/echo_body", simplePostAction)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/echo_body" to mutableMapOf("POST" to simplePostAction))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return a SimplePostAction from echo_body when http method is POST`() {
        val simplePostAction: Action = SimplePostAction()
        router.addRoute("POST", "/echo_body", simplePostAction)
        val request = Request("POST", "/echo_body")
        val action = router.routeRequest(request)
        assertIs<SimplePostAction>(action)
    }

    @Test
    fun `expect addRoute member to add OPTIONS method to method_options route`() {
        val methodOptionsController = MethodOptionsAction()
        router.addRoute("OPTIONS", "/method_options", methodOptionsController)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/method_options" to mutableMapOf("OPTIONS" to methodOptionsController))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return MethodOptionsAction from method_options route when http method is OPTIONS`() {
        router.addRoute("OPTIONS", "/method_options", MethodOptionsAction())
        val request = Request("OPTIONS", "/method_options")
        val action = router.routeRequest(request)
        assertIs<MethodOptionsAction>(action)
    }

    @Test
    fun `expect addRoute member to add OPTIONS method to method_options2 route`() {
        val methodOptions2Controller = MethodOptions2Action()
        router.addRoute("OPTIONS", "/method_options2", methodOptions2Controller)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/method_options2" to mutableMapOf("OPTIONS" to methodOptions2Controller))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return MethodOptions2Action from method_options2 route when http method is OPTIONS`() {
        router.addRoute("OPTIONS", "/method_options2", MethodOptions2Action())
        val request = Request("OPTIONS", "/method_options2")
        val action = router.routeRequest(request)
        assertIs<MethodOptions2Action>(action)
    }

    @Test
    fun `expect addRoute member to add GET method to redirect route`() {
        val redirectController = RedirectAction()
        router.addRoute("GET", "/redirect", redirectController)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/redirect" to mutableMapOf("GET" to redirectController))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return RedirectAction from redirect route when http method is GET`() {
        router.addRoute("GET", "/redirect", RedirectAction())
        val request = Request("GET", "/redirect")
        val action = router.routeRequest(request)
        assertIs<RedirectAction>(action)
    }

    @Test
    fun `expect addRoute member to add GET method to head_request`() {
        val headRequestController = HeadRequestAction()
        router.addRoute("GET", "/head_request", headRequestController)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/head_request" to mutableMapOf("GET" to headRequestController))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return HeadRequestAction from head_request route when http method is GET`() {
        router.addRoute("GET", "/head_request", HeadRequestAction())
        val request = Request("GET", "/head_request")
        val action = router.routeRequest(request)
        assertIs<HeadRequestAction>(action)
    }

    @Test
    fun `expect routeRequest to return NotFoundAction when endpoint does not exist`() {
        val request = Request("GET", "/foobar")
        val action = router.routeRequest(request)
        assertIs<NotFoundAction>(action)
    }

    @Test
    fun `expect routeRequest to return MethodNotFoundAction when invalid method for head_request`() {
        router.addRoute("HEAD", "/head_request", HeadRequestAction())
        router.addRoute("OPTIONS", "/head_request", HeadRequestAction())
        val request = Request("GET", "/head_request")
        val action = router.routeRequest(request)
        assertIs<MethodNotAllowedAction>(action)
    }

    @Test
    fun `expect addRoute to add GET method and GetHtmlResponseAction to html_response route`() {
        val getHTMLResponseAction = GetHTMLResponseAction()
        router.addRoute("GET", "/html_response", getHTMLResponseAction)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/html_response" to mutableMapOf("GET" to getHTMLResponseAction))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return GETHtmlResponseAction from html_response route when http method is GET`() {
        router.addRoute("GET", "/html_response", GetHTMLResponseAction())
        val request = Request("GET", "/html_response")
        val action = router.routeRequest(request)
        assertIs<GetHTMLResponseAction>(action)
    }

    @Test
    fun `expect addRoute to add GET method and GetJsonResponseAction to json_response route`() {
        val jsonGenerator = JsonGenerator()
        val getJsonResponseAction = GetJsonResponseAction(jsonGenerator)
        router.addRoute("GET", "/json_response", getJsonResponseAction)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/json_response" to mutableMapOf("GET" to getJsonResponseAction))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return GetJsonResponseAction from json_response route when http method is GET`() {
        val jsonGenerator = JsonGenerator()
        router.addRoute("GET", "/json_response", GetJsonResponseAction(jsonGenerator))
        val request = Request("GET", "/json_response")
        val action = router.routeRequest(request)
        assertIs<GetJsonResponseAction>(action)
    }

    @Test
    fun `expect addRoute to add GET method and GetHtmlHealthCheckAction to health-check route`() {
        val getHTMLResponseAction = GetHTMLResponseAction()
        router.addRoute("GET", "/health-check.html", getHTMLResponseAction)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/health-check.html" to mutableMapOf("GET" to getHTMLResponseAction))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return GetHTMLHealthCheckAction from health-check route when http method is GET`() {
        router.addRoute("GET", "/health-check.html", GetHTMLResponseAction())
        val request = Request("GET", "/health-check.html")
        val action = router.routeRequest(request)
        assertIs<GetHTMLResponseAction>(action)
    }

    @Test
    fun `expect addRoute to add GET method and GetXmlResponseAction to xml_response route`() {
        val getXmlResponseAction = GetXmlResponseAction()
        router.addRoute("GET", "/xml_response", getXmlResponseAction)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/xml_response" to mutableMapOf("GET" to getXmlResponseAction))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return GetXmlResponseAction from xml_response route when http method is GET`() {
        router.addRoute("GET", "/xml_response", GetXmlResponseAction())
        val request = Request("GET", "/xml_response")
        val action = router.routeRequest(request)
        assertIs<GetXmlResponseAction>(action)
    }

    @Test
    fun `expect addRoute to add POST method and CreateToDoAction to to-do route`() {
        val fileIo = FileIoMock()
        val jsonGenerator = JsonGenerator()
        val filePath = "src/test/kotlin/resources/test-task-list.txt"
        val toDoList = ToDoList(filePath, fileIo, jsonGenerator)
        val createToDoAction = CreateToDoAction(toDoList)

        router.addRoute("POST", "/todo", createToDoAction)
        val expectation: MutableMap<String, MutableMap<String, Action>> = mutableMapOf("/todo" to mutableMapOf("POST" to createToDoAction))
        assertEquals(expectation, router.routes)
    }

    @Test
    fun `expect routeRequest to return CreateToDoAction from to-do route when http method is POST`() {
        val fileIo = FileIoMock()
        val jsonGenerator = JsonGenerator()
        val filePath = "src/test/kotlin/resources/test-task-list.txt"
        val toDoList = ToDoList(filePath, fileIo, jsonGenerator)
        val createToDoAction = CreateToDoAction(toDoList)

        router.addRoute("POST", "/todo", createToDoAction)
        val request = Request("POST", "/todo")
        val action = router.routeRequest(request)
        assertIs<CreateToDoAction>(action)
    }

}
