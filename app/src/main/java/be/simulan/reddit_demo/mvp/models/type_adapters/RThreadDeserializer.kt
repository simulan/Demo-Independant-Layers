package be.simulan.reddit_demo.mvp.models.type_adapters

import be.simulan.reddit_demo.mvp.models.data.ThreadHeader
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

/**
 * @author Simulan
 * @version 1.0.0
 * @since 28/06/2017
 */
class RThreadDeserializer : JsonDeserializer<ThreadHeader> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ThreadHeader {
        val obj = (json as JsonObject).getAsJsonObject("data")
        val t : ThreadHeader = ThreadHeader()
        t.id = obj["id"].asString
        t.title = obj["title"].asString
        t.author = obj["author"].asString
        t.score = obj["score"].asLong
        t.thumbnail = ThreadHeader.Thumbnail()
        t.thumbnail.url = obj["thumbnail"].asString

        //check JsonNull
        val widthJsonProp = obj["thumbnail_width"]
        val heightJsonProp = obj["thumbnail_height"]
        if(!widthJsonProp.isJsonNull && !heightJsonProp.isJsonNull){
                t.thumbnail.dimensions = Pair(widthJsonProp.asInt,heightJsonProp.asInt)
        }else{
            t.thumbnail.dimensions = Pair(0,0)
        }
        return t
    }
}
