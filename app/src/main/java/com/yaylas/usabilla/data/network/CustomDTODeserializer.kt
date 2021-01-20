package com.yaylas.usabilla.data.network

import com.google.gson.*
import com.yaylas.usabilla.data.network.models.CustomDTO
import java.lang.reflect.Type

class CustomDTODeserializer : JsonDeserializer<CustomDTO?> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CustomDTO? {
        if (json == null || json is JsonArray) {
            return null
        }
        return Gson().fromJson(json, typeOfT)
    }
}