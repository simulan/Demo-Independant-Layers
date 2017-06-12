package be.sanderdebleecker.reddit_demo.mvp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Simulan
 * *
 * @version 1.0.0
 * *
 * @since 12/06/2017
 */

class ThreadsEnvelope {
    @SerializedName("after")
    @Expose
    var after: String? = null
    @SerializedName("before")
    @Expose
    var before: Any? = null
    @SerializedName("children")
    @Expose
    var children: List<ThreadEnvelope>? = null
    @SerializedName("modhash")
    @Expose
    var modhash: String? = null
}
