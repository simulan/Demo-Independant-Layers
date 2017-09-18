package be.simulan.reddit_demo.mvp.models.type_adapters.utils

import be.simulan.reddit_demo.mvp.models.data.Thumbnail
import com.google.gson.JsonObject

fun getThumbnail(json : JsonObject) : Thumbnail {
    val thumbnail = Thumbnail()
    thumbnail.url = json["thumbnail"].asString

    val widthJsonProp = json["thumbnail_width"]
    val heightJsonProp = json["thumbnail_height"]
    if(!widthJsonProp.isJsonNull && !heightJsonProp.isJsonNull){
        thumbnail.dimensions = Pair(widthJsonProp.asInt,heightJsonProp.asInt)
    }else{
        thumbnail.dimensions = Pair(0,0)
    }
    return thumbnail
}