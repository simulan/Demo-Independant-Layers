package be.sanderdebleecker.reddit_demo.mvp.models

import android.print.PrintAttributes.Resolution
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Simulan
 * @version 1.0.0
 * @since 12/06/2017
 */

class Thread {
    @SerializedName("approved_by")
    @Expose
    var approvedBy: Any? = null
    @SerializedName("archived")
    @Expose
    var archived: Boolean? = null
    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("author_flair_css_class")
    @Expose
    var authorFlairCssClass: Any? = null
    @SerializedName("author_flair_text")
    @Expose
    var authorFlairText: Any? = null
    @SerializedName("banned_by")
    @Expose
    var bannedBy: Any? = null
    @SerializedName("brand_safe")
    @Expose
    var brandSafe: Boolean? = null
    @SerializedName("can_gild")
    @Expose
    var canGild: Boolean? = null
    @SerializedName("clicked")
    @Expose
    var clicked: Boolean? = null
    @SerializedName("contest_mode")
    @Expose
    var contestMode: Boolean? = null
    @SerializedName("created")
    @Expose
    var created: Int? = null
    @SerializedName("created_utc")
    @Expose
    var createdUtc: Int? = null
    @SerializedName("distinguished")
    @Expose
    var distinguished: Any? = null
    @SerializedName("domain")
    @Expose
    var domain: String? = null
    @SerializedName("downs")
    @Expose
    var downs: Int? = null
    @SerializedName("edited")
    @Expose
    var edited: Boolean? = null
    @SerializedName("gilded")
    @Expose
    var gilded: Int? = null
    @SerializedName("hidden")
    @Expose
    var hidden: Boolean? = null
    @SerializedName("hide_score")
    @Expose
    var hideScore: Boolean? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("is_self")
    @Expose
    var isSelf: Boolean? = null
    @SerializedName("is_video")
    @Expose
    var isVideo: Boolean? = null
    @SerializedName("likes")
    @Expose
    var likes: Any? = null
    @SerializedName("link_flair_css_class")
    @Expose
    var linkFlairCssClass: Any? = null
    @SerializedName("link_flair_text")
    @Expose
    var linkFlairText: Any? = null
    @SerializedName("locked")
    @Expose
    var locked: Boolean? = null
    @SerializedName("media")
    @Expose
    var media: Any? = null
    @SerializedName("media_embed")
    @Expose
    var mediaEmbed: MediaEmbed? = null
    @SerializedName("mod_reports")
    @Expose
    var modReports: List<Any>? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("num_comments")
    @Expose
    var numComments: Int? = null
    @SerializedName("num_reports")
    @Expose
    var numReports: Any? = null
    @SerializedName("over_18")
    @Expose
    var over18: Boolean? = null
    @SerializedName("permalink")
    @Expose
    var permalink: String? = null
    @SerializedName("post_hint")
    @Expose
    var postHint: String? = null
    @SerializedName("preview")
    @Expose
    var preview: Preview? = null
    @SerializedName("quarantine")
    @Expose
    var quarantine: Boolean? = null
    @SerializedName("removal_reason")
    @Expose
    var removalReason: Any? = null
    @SerializedName("report_reasons")
    @Expose
    var reportReasons: Any? = null
    @SerializedName("saved")
    @Expose
    var saved: Boolean? = null
    @SerializedName("score")
    @Expose
    var score: Int? = null
    @SerializedName("secure_media")
    @Expose
    var secureMedia: Any? = null
    @SerializedName("secure_media_embed")
    @Expose
    var secureMediaEmbed: SecureMediaEmbed? = null
    @SerializedName("selftext")
    @Expose
    var selftext: String? = null
    @SerializedName("selftext_html")
    @Expose
    var selftextHtml: String? = null
    @SerializedName("spoiler")
    @Expose
    var spoiler: Boolean? = null
    @SerializedName("stickied")
    @Expose
    var stickied: Boolean? = null
    @SerializedName("subreddit")
    @Expose
    var subreddit: String? = null
    @SerializedName("subreddit_id")
    @Expose
    var subredditId: String? = null
    @SerializedName("subreddit_name_prefixed")
    @Expose
    var subredditNamePrefixed: String? = null
    @SerializedName("subreddit_type")
    @Expose
    var subredditType: String? = null
    @SerializedName("suggested_sort")
    @Expose
    var suggestedSort: Any? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null
    @SerializedName("thumbnail_height")
    @Expose
    var thumbnailHeight: Any? = null
    @SerializedName("thumbnail_width")
    @Expose
    var thumbnailWidth: Any? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("ups")
    @Expose
    var ups: Int? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("user_reports")
    @Expose
    var userReports: List<Any>? = null
    @SerializedName("view_count")
    @Expose
    var viewCount: Any? = null
    @SerializedName("visited")
    @Expose
    var visited: Boolean? = null

    inner class MediaEmbed {
    }
    inner class Preview {
        @SerializedName("enabled")
        @Expose
        var enabled: Boolean? = null
        @SerializedName("images")
        @Expose
        var images: List<Image>? = null
    }
    inner class Image {
        @SerializedName("id")
        @Expose
        var id: String? = null
        @SerializedName("resolutions")
        @Expose
        var resolutions: List<Resolution>? = null
        @SerializedName("source")
        @Expose
        var source: Source? = null
        @SerializedName("variants")
        @Expose
        var variants: Variants? = null
    }
    inner class Source {
        @SerializedName("height")
        @Expose
        var height: Int? = null
        @SerializedName("url")
        @Expose
        var url: String? = null
        @SerializedName("width")
        @Expose
        var width: Int? = null
    }
    inner class Variants {
    }
    inner class SecureMediaEmbed {
    }
}