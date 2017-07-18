package be.simulan.reddit_demo.mvp.views.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.mvp.models.data.ThumbnailItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_thumbnail.*

class ThumbnailFragment : Fragment() {
    private lateinit var item : ThumbnailItem

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View = inflater!!.inflate(R.layout.fragment_thumbnail,container,false)
    override fun onStart() {
        super.onStart()
        showItem()
    }
    private fun showItem() {
        txtv_title.text = item.title
        Picasso.with(activity).load(item.thumbnail.url)
                .placeholder(R.raw.placeholder)
                .fit().centerCrop().into(imgv_thumbnail)
    }
    fun setItem(thumbnail : ThumbnailItem): ThumbnailFragment {
        item = thumbnail
        return this
    }
}