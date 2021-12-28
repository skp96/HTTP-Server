package Utilities

import kotlin.test.*

class JsonGeneratorTest {
    @Test
    fun `resourceToJson returns a string formatted JSON object`() {
        val jsonGenerator = JsonGenerator()
        val result = jsonGenerator.resourceToJson(mapOf("key1" to "value1", "key2" to "value2"))
        val expectation = """{"key1":"value1","key2":"value2"}"""
        assertEquals(expectation, result)
    }
}
