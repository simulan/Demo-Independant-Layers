package be.simulan.reddit_demo.mvp.models.type_adapters

import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import be.simulan.reddit_demo.mvp.models.type_adapters.utils.asThread
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class ThreadDeserializer : JsonDeserializer<ThreadItem> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ThreadItem {
        return (json as JsonObject).getAsJsonObject("data").asThread()
    }
}
