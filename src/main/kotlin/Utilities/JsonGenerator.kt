package Utilities

import com.google.gson.Gson
import kotlin.reflect.KClass

class JsonGenerator {
    private val gson = Gson()

    fun resourceToJson(resource: Any): String {
        return gson.toJson(resource)
    }

    fun <T: Any> resourceFromJson(resource: String, obj: KClass<T>): T {
        return gson.fromJson(resource, obj.java)
    }
}
