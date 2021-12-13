package utilities

import org.json.JSONObject

class JsonGenerator {
    private val jsonObj = JSONObject()

    fun generateJsonString(mapOfKeysToValues: Map<String, Any>): String {
        for (entries in mapOfKeysToValues) {
            jsonObj.put(entries.key, entries.value)
        }
        return jsonObj.toString()
    }
}
