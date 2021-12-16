package HttpServerErrors

import httpstatus.HttpStatus
import mocks.HTTPResponseBuilderMock
import kotlin.test.*
import HttpServerErrors.InternalServerError

class InternalServerErrorTest {
    @Test
    fun `handleError sets the correct properties on ResponseBuilder`() {
        val responseBuilder = HTTPResponseBuilderMock()
        val internalServerError = InternalServerError()

        internalServerError.handleError(responseBuilder)

        assertEquals(HttpStatus.ServerError, responseBuilder.httpStatusCode)
    }
}
