package be.simulan.reddit_demo.mvp.models.type_adapters.utils

import be.simulan.reddit_demo.mvp.models.data.Comment
import com.google.gson.JsonElement
import com.google.gson.JsonObject

fun getComment(json : JsonObject) : Comment {
    val comment = Comment()
    comment.author = json["author"].asString
    comment.text = json["body"].asString
    return comment
}
fun JsonElement.asComment() : Comment {
    return getComment(this.asJsonObject)
}