package be.simulan.reddit_demo.thread.models.deserializers

import be.simulan.reddit_demo.thread.models.ThreadItem
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class ThreadCollectionDeserializer : JsonDeserializer<Array<ThreadItem>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Array<ThreadItem> {
        val children = (json as JsonObject).getAsJsonObject("data").getAsJsonArray("children")
        val threads : Array<ThreadItem> = Array(children.size(), {
            index -> children.get(index).asJsonObject["data"].asThread()
        })
        return threads
    }
}

