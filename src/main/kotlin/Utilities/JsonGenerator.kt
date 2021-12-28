package Utilities

import com.google.gson.Gson

class JsonGenerator {
    private val gson = Gson()

    fun resourceToJson(resource: Any): String {
        return gson.toJson(resource)
    }
}
