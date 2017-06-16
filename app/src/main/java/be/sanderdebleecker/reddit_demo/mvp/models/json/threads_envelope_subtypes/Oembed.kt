package be.sanderdebleecker.reddit_demo.mvp.models.json.threads_envelope_subtypes

import com.google.gson.annotations.SerializedName

class Oembed {

    @SerializedName("author_name")
    var authorName: String? = null
    @SerializedName("author_url")
    var authorUrl: String? = null
    @SerializedName("height")
    var height: Long? = null
    @SerializedName("html")
    var html: String? = null
    @SerializedName("provider_name")
    var providerName: String? = null
    @SerializedName("provider_url")
    var providerUrl: String? = null
    @SerializedName("thumbnail_height")
    var thumbnailHeight: Long? = null
    @SerializedName("thumbnail_url")
    var thumbnailUrl: String? = null
    @SerializedName("thumbnail_width")
    var thumbnailWidth: Long? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("type")
    var type: String? = null
    @SerializedName("version")
    var version: String? = null
    @SerializedName("width")
    var width: Long? = null

}
