package be.simulan.reddit_demo.mvp.views.adapters.viewholders

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.mvp.models.data.RThread
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_thread.view.*

class ThreadViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup.inflate(R.layout.row_thread)) {
    val gradientType = GradientDrawable.Orientation.BOTTOM_TOP

    fun bind(item: RThread) = with(itemView) {
        title.text = item.title
        author.text = item.author
        loadThumbnail(context, item.thumbnail.url, thumbnail)
        indicator.background = getScoreIndicator(item.score)
    }

    private fun loadThumbnail(context: Context, url: String, imageView: ImageView) {
        Picasso.with(context).cancelRequest(imageView)
        loadDefaultThumbnail(context, imageView)
        if (isCustomThumbnail(url)) {
            loadCustomThumbnail(context, url, imageView)
        }
    }
    private fun isCustomThumbnail(url: String) = url !in arrayOf("", "self", "default")
    private fun loadDefaultThumbnail(context: Context, imageView: ImageView) {
        Picasso.with(context).load(R.mipmap.ic_launcher).placeholder(R.drawable.progress_animation).into(imageView)
    }
    private fun loadCustomThumbnail(context: Context, url: String, imageView: ImageView) {
        Picasso.with(context).load(url).centerCrop().into(imageView)
    }
    private fun getScoreIndicator(score : Long) = GradientDrawable(gradientType, ScoreGradient.get(score).colors)
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}