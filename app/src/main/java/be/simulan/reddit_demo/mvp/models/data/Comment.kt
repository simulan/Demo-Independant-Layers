package be.simulan.reddit_demo.mvp.models.data

import com.google.gson.annotations.SerializedName

class Comment(var childs : MutableList<Comment>) {
    var author : String = ""
    @SerializedName("body")
    var text : String = ""
}