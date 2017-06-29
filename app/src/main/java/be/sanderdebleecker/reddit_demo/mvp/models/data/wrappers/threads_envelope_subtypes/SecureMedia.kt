package be.sanderdebleecker.reddit_demo.mvp.models.data.wrappers.threads_envelope_subtypes

import com.google.gson.annotations.SerializedName

class SecureMedia {

    @SerializedName("oembed")
    var oembed: Oembed? = null
    @SerializedName("type")
    var type: String? = null

}
