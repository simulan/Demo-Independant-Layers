package be.sanderdebleecker.reddit_demo.mvp.models.json.threads_envelope_subtypes

import com.google.gson.annotations.SerializedName

class Source {

    @SerializedName("height")
    var height: Long? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("width")
    var width: Long? = null

}
