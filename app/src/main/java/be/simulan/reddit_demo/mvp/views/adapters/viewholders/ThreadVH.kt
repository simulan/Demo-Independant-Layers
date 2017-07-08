package be.simulan.reddit_demo.mvp.views.adapters.viewholders

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.mvp.models.data.ThreadHeader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_thread.view.*

class ThreadVH(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener, ThreadVHListener {
    private var threadId : String = ""
    private val gradientType = GradientDrawable.Orientation.BOTTOM_TOP
    private var clickedThumbnail = false
    private var hasCustomThumbnail = false

    fun bind(item: ThreadHeader) = with(itemView) {
        threadId = item.id
        title.text = item.title
        author.text = item.author
        loadThumbnail(context, item.thumbnail.url, thumbnail)
        indicator.background = getScoreIndicator(item.score)
    }

    private fun loadThumbnail(context: Context, url: String, imageView: ImageView) {
        Picasso.with(context).cancelRequest(imageView)
        loadDefaultThumbnail(context, imageView)
        hasCustomThumbnail=false
        if (isCustomThumbnail(url)) {
            hasCustomThumbnail=true
            loadCustomThumbnail(context, url, imageView)
        }
    }
    private fun isCustomThumbnail(url: String) = url !in arrayOf("", "self", "default")
    private fun loadDefaultThumbnail(context: Context, imageView: ImageView) {
        Picasso.with(context).load(R.mipmap.ic_launcher).placeholder(R.drawable.progress_animation).into(imageView)
    }
    private fun loadCustomThumbnail(context: Context, url: String, imageView: ImageView) {
        Picasso.with(context).load(url).fit().centerCrop().into(imageView)
    }
    private fun getScoreIndicator(score : Long) = GradientDrawable(gradientType, ScoreGradient.get(score).colors)

    override fun onClick(v: View?) {
        clickedThumbnail = v!=null && v==itemView.thumbnail
    }
    override fun isThumbnailClicked() = clickedThumbnail && hasCustomThumbnail
    override fun getThreadId(): String = threadId
}

