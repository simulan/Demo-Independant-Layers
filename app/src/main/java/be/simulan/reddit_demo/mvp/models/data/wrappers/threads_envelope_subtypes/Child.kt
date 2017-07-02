package be.simulan.reddit_demo.mvp.models.data.wrappers.threads_envelope_subtypes

import com.google.gson.annotations.SerializedName

class Child {

    @SerializedName("data")
    var data: Data? = null
    @SerializedName("kind")
    var kind: String? = null

}
