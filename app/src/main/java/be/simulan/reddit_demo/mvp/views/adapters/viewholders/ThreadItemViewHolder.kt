package be.simulan.reddit_demo.mvp.views.adapters.viewholders

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import com.jakewharton.rxbinding2.view.RxView
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import kotlinx.android.synthetic.main.row_thread.view.*


class ThreadItemViewHolder(view: View) : RecyclerView.ViewHolder(view), ThreadItemView {
    private var threadId : String = ""
    private val gradientType = GradientDrawable.Orientation.BOTTOM_TOP

    fun bind(item: ThreadItem) = with(itemView) {
        threadId = item.id
        title.text = item.title
        author.text = item.author
        loadThumbnail(context, item.thumbnail.url, thumbnail)
        indicator.background = getScoreGradient(item.score)
    }

    private fun loadThumbnail(context: Context, url: String, imageView: ImageView) {
        Picasso.with(context).cancelRequest(imageView)
        loadDefaultThumbnail(context, imageView)
        if (isCustomThumbnail(url)) {
            loadCustomThumbnail(context, url, imageView)
        }
    }
    private fun isCustomThumbnail(url: String) : Boolean = url !in arrayOf("", "self", "default")
    private fun loadDefaultThumbnail(context: Context, imageView: ImageView) {
        Picasso.with(context).load(R.mipmap.ic_launcher).placeholder(R.drawable.progress_animation).into(imageView)
    }
    private fun loadCustomThumbnail(context: Context, url: String, imageView: ImageView) {
        Picasso.with(context).load(url).fit().centerCrop().into(imageView)
    }
    private fun getScoreGradient(score : Long) : GradientDrawable = GradientDrawable(gradientType, ScoreGradient.get(score).colors)

    override fun getThumbnailClicks() : Observable<String> = RxView.clicks(itemView.thumbnail).map { getThreadId() }
    override fun getThreadClicks() : Observable<String> = RxView.clicks(itemView.thread).map { getThreadId() }
    override fun getThreadId(): String = threadId
}

