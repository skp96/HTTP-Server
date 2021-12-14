import httpstatus.HttpStatus
import mocks.HTTPResponseBuilderMock
import kotlin.test.*

class ServerErrorTest {
    @Test
    fun `handleError sets the correct properties on ResponseBuilder`() {
        val responseBuilder = HTTPResponseBuilderMock()
        val serverError = ServerError()

        serverError.handleError(responseBuilder)

        assertEquals(HttpStatus.ServerError, responseBuilder.httpStatusCode)
    }
}
