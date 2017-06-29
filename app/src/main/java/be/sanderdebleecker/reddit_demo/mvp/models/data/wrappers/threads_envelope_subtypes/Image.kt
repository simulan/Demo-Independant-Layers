package be.sanderdebleecker.reddit_demo.mvp.models.data.wrappers.threads_envelope_subtypes

import com.google.gson.annotations.SerializedName

class Image {

    @SerializedName("id")
    var id: String? = null
    @SerializedName("resolutions")
    var resolutions: List<Resolution>? = null
    @SerializedName("source")
    var source: Source? = null
    @SerializedName("variants")
    var variants: Variants? = null

}
