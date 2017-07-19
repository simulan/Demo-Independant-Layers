package be.simulan.reddit_demo.mvp.views.sub

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.mvp.models.data.ThumbnailItem
import be.simulan.reddit_demo.mvp.views.inflate
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ThumbnailView(val container : ViewGroup ,val item : ThumbnailItem) {
    @Inject internal lateinit var context : Context

    fun show() {
        val v : View = container.inflate(R.layout.view_thumbnail)
        load(v)
    }
    private fun load(v : View) {
        val title = v.findViewById(R.id.txtv_thumbnail_title) as TextView
        val imgv = v.findViewById(R.id.imgv_thumbnail) as ImageView
        title.text = item.title
        Picasso.with(context).load(item.thumbnail.url)
                .placeholder(R.raw.placeholder)
                .fit().centerCrop().into(imgv)
    }
    fun hide() {
        container.removeAllViews()
    }
}