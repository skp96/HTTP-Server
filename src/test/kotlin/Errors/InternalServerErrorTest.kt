package Errors

import httpstatus.HttpStatus
import mocks.HTTPResponseBuilderMock
import kotlin.test.*

class InternalServerErrorTest {
    @Test
    fun `handleError sets the correct properties on ResponseBuilder`() {
        val responseBuilder = HTTPResponseBuilderMock()
        val internalServerError = InternalServerError()

        internalServerError.handleError(responseBuilder)

        assertEquals(HttpStatus.ServerError, responseBuilder.httpStatusCode)
    }
}
