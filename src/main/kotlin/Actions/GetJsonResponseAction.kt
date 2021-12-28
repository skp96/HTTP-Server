package Actions

import Utilities.JsonGenerator
import contenttype.HttpContentTypes
import request.Request
import response.ResponseBuilder

class GetJsonResponseAction(private val jsonGenerator: JsonGenerator) : Action {
    private val headers = mapOf("Content-Type" to (HttpContentTypes.JSON.type + HttpContentTypes.JSON.parameter))
    private lateinit var requestBody: String

    override fun act(responseBuilder: ResponseBuilder, request: Request): String {
        val responseBody = jsonGenerator.resourceToJson(mapOf("key1" to "value1", "key2" to "value2"))
        responseBuilder.setHeaders(headers)
        responseBuilder.setBody(responseBody)
        return responseBuilder.build()
    }

    override fun setBody(body: String) {
        requestBody = body
    }
}
