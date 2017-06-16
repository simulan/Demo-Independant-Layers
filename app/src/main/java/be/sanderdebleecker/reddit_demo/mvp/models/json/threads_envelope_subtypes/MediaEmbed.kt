package be.sanderdebleecker.reddit_demo.mvp.models.json.threads_envelope_subtypes

import com.google.gson.annotations.SerializedName

class MediaEmbed {

    @SerializedName("content")
    var content: String? = null
    @SerializedName("height")
    var height: Long? = null
    @SerializedName("scrolling")
    var scrolling: Boolean? = null
    @SerializedName("width")
    var width: Long? = null

}
