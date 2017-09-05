package be.simulan.reddit_demo.mvp.models.type_adapters

import be.simulan.reddit_demo.mvp.models.data.Comment
import com.google.gson.*
import java.lang.reflect.Type

class CommentDeserializer : JsonDeserializer<Array<Comment>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Array<Comment> {
        val jsonRoot = json as JsonArray
        val jsonCommentsListingObject = getCommentsListing(jsonRoot)
        val jsonComments = getComments(jsonCommentsListingObject)
        val comments : Array<Comment> = Array<Comment>(jsonComments.size(), {
            index -> deserializeJsonComment(jsonComments[index].asJsonObject)
        })
        return comments
    }
    private fun getCommentsListing(jsonRoot: JsonArray) : JsonObject {
        val indexOfCommentsListing : Int = 1
        return jsonRoot[indexOfCommentsListing].asJsonObject
    }
    private fun getComments(commentsListing : JsonObject) : JsonArray {
        val comments : JsonArray = commentsListing["data"].asJsonObject["children"].asJsonArray
        removeMoreSection(comments)
        return comments
    }
    private fun removeMoreSection(comments: JsonArray) {
        val moreSection = comments.size()-1
        comments.remove(moreSection)
    }
    private fun deserializeJsonComment(json : JsonObject) : Comment {
        val commentData : JsonObject = json["data"].asJsonObject
        val comment = Comment()
        comment.author = commentData["author"].asString
        comment.text = commentData["body"].asString
        return comment
    }
}
