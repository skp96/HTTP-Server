package Utilities

import kotlin.test.*
import org.json.JSONObject
import utilities.JsonGenerator

class JsonGeneratorTest {
    @Test
    fun `generateJson returns a string formatted JSON object`() {
        val jsonGenerator = JsonGenerator()
        val result = jsonGenerator.generateJsonString(mapOf("key1" to "value1", "key2" to "value2"))
        val expectation = JSONObject()
        expectation.put("key1", "value1")
        expectation.put("key2", "value2")
        assertEquals(expectation.toString(), result)
    }
}
