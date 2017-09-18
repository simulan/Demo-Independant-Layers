package be.simulan.reddit_demo.mvp.models.type_adapters

import be.simulan.reddit_demo.mvp.models.data.Comment
import be.simulan.reddit_demo.mvp.models.data.enveloppe.CommentConversion
import be.simulan.reddit_demo.mvp.models.type_adapters.utils.asComment
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

/**
 * Using CommentConversion as intermediate result model because api returns an Array of Arrays breaking linearity with
 * retrofits call to the typeAdapter, e.g. typeadapter gets called first for array of 0 comments, typeadapter gets
 * called afterwards for 5 comments. Only allowing the code afterwards to collect a total of comments
 */

class CommentDeserializer : JsonDeserializer<CommentConversion> {
    val KIND_COMMENT = "t1"

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): CommentConversion {
        if(json!!.isJsonObject) {
            val unboxedArray =  json.asJsonObject["data"].asJsonObject["children"].asJsonArray
            val comments = Array(unboxedArray.size()) {
                index -> getComment(unboxedArray[index].asJsonObject)
            }
            comments.filter { !it.author.equals("")}
            return CommentConversion(comments)
        }else{
            throw Exception("Hey this one is useless!")
        }

    }
    private fun getComment(item : JsonObject ) : Comment {
        if(isComment(item)){
            val commentData = item["data"].asJsonObject
            return commentData.asComment()
        }else{
            return Comment()
        }

    }
    private fun isComment(jsonObject : JsonObject) = jsonObject["kind"].asString.equals(KIND_COMMENT)

}
