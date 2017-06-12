package be.sanderdebleecker.reddit_demo.mvp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Simulan
 * @version 1.0.0
 * @since 12/06/2017
 */
class ThreadEnvelope {
    @SerializedName("data")
    @Expose
    var data: Thread? = null
    @SerializedName("kind")
    @Expose
    var kind: String? = null
}