package be.simulan.reddit_demo.mvp.models.data.wrappers.threads_envelope_subtypes

import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("after")
    var after: String? = null
    @SerializedName("approved_by")
    var approvedBy: Any? = null
    @SerializedName("archived")
    var archived: Boolean? = null
    @SerializedName("author")
    var author: String? = null
    @SerializedName("author_flair_css_class")
    var authorFlairCssClass: String? = null
    @SerializedName("author_flair_text")
    var authorFlairText: String? = null
    @SerializedName("banned_by")
    var bannedBy: Any? = null
    @SerializedName("before")
    var before: String? = null
    @SerializedName("brand_safe")
    var brandSafe: Boolean? = null
    @SerializedName("can_gild")
    var canGild: Boolean? = null
    @SerializedName("children")
    var children: List<Child>? = null
    @SerializedName("clicked")
    var clicked: Boolean? = null
    @SerializedName("contest_mode")
    var contestMode: Boolean? = null
    @SerializedName("created")
    var created: Double? = null
    @SerializedName("created_utc")
    var createdUtc: Double? = null
    @SerializedName("distinguished")
    var distinguished: Any? = null
    @SerializedName("domain")
    var domain: String? = null
    @SerializedName("downs")
    var downs: Long? = null
    @SerializedName("edited")
    var edited: String? = null
    @SerializedName("facets")
    var facets: Facets? = null
    @SerializedName("gilded")
    var gilded: Long? = null
    @SerializedName("hidden")
    var hidden: Boolean? = null
    @SerializedName("hide_score")
    var hideScore: Boolean? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("is_self")
    var isSelf: Boolean? = null
    @SerializedName("is_video")
    var isVideo: Boolean? = null
    @SerializedName("likes")
    var likes: Any? = null
    @SerializedName("link_flair_css_class")
    var linkFlairCssClass: String? = null
    @SerializedName("link_flair_text")
    var linkFlairText: String? = null
    @SerializedName("locked")
    var locked: Boolean? = null
    @SerializedName("media")
    var media: Media? = null
    @SerializedName("media_embed")
    var mediaEmbed: MediaEmbed? = null
    @SerializedName("mod_reports")
    var modReports: List<Any>? = null
    @SerializedName("modhash")
    var modhash: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("num_comments")
    var numComments: Long? = null
    @SerializedName("num_reports")
    var numReports: Any? = null
    @SerializedName("over_18")
    var over18: Boolean? = null
    @SerializedName("permalink")
    var permalink: String? = null
    @SerializedName("post_hint")
    var postHint: String? = null
    @SerializedName("preview")
    var preview: Preview? = null
    @SerializedName("quarantine")
    var quarantine: Boolean? = null
    @SerializedName("removal_reason")
    var removalReason: Any? = null
    @SerializedName("report_reasons")
    var reportReasons: Any? = null
    @SerializedName("saved")
    var saved: Boolean? = null
    @SerializedName("score")
    var score: Long? = null
    @SerializedName("secure_media")
    var secureMedia: SecureMedia? = null
    @SerializedName("secure_media_embed")
    var secureMediaEmbed: SecureMediaEmbed? = null
    @SerializedName("selftext")
    var selftext: String? = null
    @SerializedName("selftext_html")
    var selftextHtml: Any? = null
    @SerializedName("spoiler")
    var spoiler: Boolean? = null
    @SerializedName("stickied")
    var stickied: Boolean? = null
    @SerializedName("subreddit")
    var subreddit: String? = null
    @SerializedName("subreddit_id")
    var subredditId: String? = null
    @SerializedName("subreddit_name_prefixed")
    var subredditNamePrefixed: String? = null
    @SerializedName("subreddit_type")
    var subredditType: String? = null
    @SerializedName("suggested_sort")
    var suggestedSort: Any? = null
    @SerializedName("thumbnail")
    var thumbnail: String? = null
    @SerializedName("thumbnail_height")
    var thumbnailHeight: Int? = null
    @SerializedName("thumbnail_width")
    var thumbnailWidth: Int? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("ups")
    var ups: Long? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("user_reports")
    var userReports: List<Any>? = null
    @SerializedName("view_count")
    var viewCount: Any? = null
    @SerializedName("visited")
    var visited: Boolean? = null

}
