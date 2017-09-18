package be.simulan.reddit_demo.mvp.models.type_adapters.utils

import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import com.google.gson.JsonElement
import com.google.gson.JsonObject

fun getThread(json : JsonObject) : ThreadItem {
    val thread = ThreadItem()
    thread.id = json["name"].asString
    thread.title = json["title"].asString
    thread.author = json["author"].asString
    thread.score = json["score"].asLong
    thread.type = getType(json["url"].asString)
    thread.thumbnail = getThumbnail(json)
    return thread
}
fun JsonElement.asThread() : ThreadItem {
    return getThread(this.asJsonObject)
}