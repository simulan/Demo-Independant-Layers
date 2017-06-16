package be.sanderdebleecker.reddit_demo.mvp.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import be.sanderdebleecker.reddit_demo.R
import be.sanderdebleecker.reddit_demo.commons.inflate
import be.sanderdebleecker.reddit_demo.mvp.models.data.RThread
import kotlinx.android.synthetic.main.row_thread.view.*


/**
 * @author Simulan
 * @version 1.0.0
 * @since 16/06/2017
 */

class ThreadsAdapter : RecyclerView.Adapter<ThreadsAdapter.ViewHolder>() {
    var items : MutableList<RThread> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder? = ViewHolder(viewGroup)
    override fun onBindViewHolder(vh: ViewHolder?, i: Int) { vh?.bind(getItem(i)) }
    override fun getItemCount(): Int = items.count()
    private fun getItem(position: Int): RThread = items[position]
    inner class ViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup.inflate(R.layout.row_thread)) {
        fun bind(item : RThread) = with(itemView) {
            title.text = item.title
            author.text = item.author
        }
    }
}
