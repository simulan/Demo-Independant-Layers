package be.sanderdebleecker.reddit_demo.mvp.models.json.threads_envelope_subtypes

import com.google.gson.annotations.SerializedName

class Preview {

    @SerializedName("enabled")
    var enabled: Boolean? = null
    @SerializedName("images")
    var images: List<Image>? = null

}
