package Utilities

import com.google.gson.Gson
import org.json.JSONObject

class JsonGenerator {
    private val jsonObj = JSONObject()
    private val gson = Gson()

    fun generateJsonString(mapOfKeysToValues: Map<String, Any>): String {
        for (entries in mapOfKeysToValues) {
            jsonObj.put(entries.key, entries.value)
        }
        return jsonObj.toString()
    }

    fun resourceToJson(resource: Any): String {
        return gson.toJson(resource)
    }
}
