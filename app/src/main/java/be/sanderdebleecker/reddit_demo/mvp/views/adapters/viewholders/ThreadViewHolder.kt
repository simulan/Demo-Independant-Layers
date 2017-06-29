package be.sanderdebleecker.reddit_demo.mvp.views.adapters.viewholders

import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import be.sanderdebleecker.reddit_demo.R
import be.sanderdebleecker.reddit_demo.commons.inflate
import be.sanderdebleecker.reddit_demo.mvp.models.data.RThread
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_thread.view.*

/**
 * @author Simulan
 * @version 1.0.0
 * @since 19/06/2017
 */

val gradBlue1 = intArrayOf(0xFF3d6883.toInt(), 0xFFaccbe9.toInt())
val gradBlue2 = intArrayOf(0xFF3d6883.toInt(), 0xFF8ebfe7.toInt())
val gradBlue3 = intArrayOf(0xFF3d6883.toInt(), 0xFF67baf4.toInt())
val gradBlue4 = intArrayOf(0xFF3d6883.toInt(), 0xFF2078b4.toInt())
val gradBlue5 = intArrayOf(0xFF3d6883.toInt(), 0xFF0d85d7.toInt())

val gradType = GradientDrawable.Orientation.BOTTOM_TOP
val popularity = arrayOf(5L, 10L, 30L, 60L)

class ThreadViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup.inflate(R.layout.row_thread)) {
    fun bind(item: RThread) = with(itemView) {
        title.text = item.title
        author.text = item.author
        Picasso.with(context).cancelRequest(thumbnail)
        if (item.thumbnail.url !in arrayOf("","self","default")) {
            Picasso.with(context).load(item.thumbnail.url).placeholder(R.drawable.progress_animation).into(thumbnail)
        } else {
            Picasso.with(context).load(R.mipmap.ic_launcher).placeholder(R.drawable.progress_animation).into(thumbnail)
        }
        indicator.background = when {
            item.score < popularity[0] -> GradientDrawable(gradType, gradBlue1)
            item.score < popularity[1] -> GradientDrawable(gradType, gradBlue2)
            item.score < popularity[2] -> GradientDrawable(gradType, gradBlue3)
            item.score < popularity[3] -> GradientDrawable(gradType, gradBlue4)
            else -> GradientDrawable(gradType, gradBlue5)
        }

    }
}