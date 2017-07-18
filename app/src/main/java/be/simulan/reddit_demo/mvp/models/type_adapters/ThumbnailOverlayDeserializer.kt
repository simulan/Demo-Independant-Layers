package be.simulan.reddit_demo.mvp.models.type_adapters

import be.simulan.reddit_demo.mvp.models.data.Thumbnail
import be.simulan.reddit_demo.mvp.models.data.ThumbnailItem
import com.google.gson.*
import java.lang.reflect.Type

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
        return  ThumbnailItem(title,thumbnail)
    }
}