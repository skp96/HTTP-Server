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

    @Test
    fun `resourceFromJson returns an object`() {
        val jsonGenerator = JsonGenerator()
        val result = jsonGenerator.resourceFromJson("""{"key1":"value1","key2":"value2"}""", mutableMapOf<String, String>()::class)
        val expectation = mapOf("key1" to "value1", "key2" to "value2")
        assertEquals(expectation, result)
    }

    @Test
    fun `resourceFromJson returns a resource`() {
        data class Data(val id: Int, val data: String)
        val resource = Data(1, "Test Data")
        val jsonGenerator = JsonGenerator()
        val jsonString = jsonGenerator.resourceToJson(resource)
        val result = jsonGenerator.resourceFromJson(jsonString, Data::class)
        assertIs<Data>(result)
    }
}
