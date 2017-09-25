package be.simulan.reddit_demo.thread.models.deserializers

import be.simulan.reddit_demo.thread.models.Thumbnail
import be.simulan.reddit_demo.thread.models.ThumbnailItem
import com.google.gson.*
import java.lang.reflect.Type

/*
* Thumbnails are hosted on reddit, most thumbnails are too small to show in full screen size
* */

class ThumbnailOverlayDeserializer : JsonDeserializer<ThumbnailItem> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ThumbnailItem {
        val children : JsonArray = (json as JsonObject).getAsJsonObject("data").getAsJsonArray("children")
        val obj = (children[0] as JsonObject).getAsJsonObject("data")

        val title : String = obj["title"].asString
        val thumbnail : Thumbnail = Thumbnail()
        thumbnail.url = obj["thumbnail"].asString

        val widthJsonProp = obj["thumbnail_width"]
        val heightJsonProp = obj["thumbnail_height"]
        if(!widthJsonProp.isJsonNull && !heightJsonProp.isJsonNull){
            thumbnail.dimensions = Pair(widthJsonProp.asInt,heightJsonProp.asInt)
        }else{
            thumbnail.dimensions = Pair(0,0)
        }
        return ThumbnailItem(title, thumbnail)
    }
}