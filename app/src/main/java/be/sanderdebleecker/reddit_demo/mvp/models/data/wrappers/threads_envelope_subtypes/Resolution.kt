package be.sanderdebleecker.reddit_demo.mvp.models.data.wrappers.threads_envelope_subtypes

import com.google.gson.annotations.SerializedName

class Resolution {

    @SerializedName("height")
    var height: Long? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("width")
    var width: Long? = null

}
