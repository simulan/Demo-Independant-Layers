package be.simulan.reddit_demo.mvp.models.convert.utils

import be.simulan.reddit_demo.comments.models.Comment
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

fun getComment(json : JsonObject) : Comment {
    val comment = Comment()
    comment.author = json["author"].asString
    comment.text = json["body"].asString
    if(hasReplies(json)) {
        val jsonReplies = getReplies(json)
        val iterator = jsonReplies.iterator()
        var replies = MutableList<Comment>(jsonReplies.size()) {
            val nextElement = iterator.next()
            getComment(nextElement.asJsonObject["data"].asJsonObject)
        }
        comment.childs.addAll(replies)
    }
    return comment
}
fun hasReplies(json : JsonObject) : Boolean = json.get("replies").isJsonObject
fun getReplies(json : JsonObject) : JsonArray
        = json["replies"].asJsonObject["data"].asJsonObject["children"].asJsonArray

fun JsonElement.asComment() : Comment {
    return getComment(this.asJsonObject)
}