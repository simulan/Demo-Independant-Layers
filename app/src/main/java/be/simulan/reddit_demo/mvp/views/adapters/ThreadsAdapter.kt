package be.simulan.reddit_demo.mvp.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import be.simulan.reddit_demo.mvp.models.data.RThread
import be.simulan.reddit_demo.mvp.views.adapters.viewholders.ThreadViewHolder

/**
 * @author Simulan
 * @version 1.0.0
 * @since 16/06/2017
 */

class ThreadsAdapter() : RecyclerView.Adapter<ThreadViewHolder>(),ThreadsArrayList {
    private var list = ArrayList<RThread>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int): ThreadViewHolder? = ThreadViewHolder(viewGroup)
    override fun onBindViewHolder(vh: ThreadViewHolder?, index: Int) { vh?.bind(getItem(index)) }
    override fun getItemCount(): Int = list.size
    private fun getItem(index: Int): RThread = list[index]

    override fun add(additions : List<RThread>) {
        val prevItemCount : Int = itemCount
        list.addAll(additions)
        notifyItemRangeInserted(prevItemCount,additions.size)
    }
    override fun clear() {
        val prevItemCount : Int = itemCount
        list.clear()
        notifyItemRangeRemoved(0,prevItemCount)
    }
    override fun getLastId() : String {
        return if(list.isNotEmpty()) list.last().id else ""
    }
}


