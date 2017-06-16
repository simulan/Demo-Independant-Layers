package be.sanderdebleecker.reddit_demo.mvp.models.json.threads_envelope_subtypes

import com.google.gson.annotations.SerializedName

class Child {

    @SerializedName("data")
    var data: Data? = null
    @SerializedName("kind")
    var kind: String? = null

}
