package be.sanderdebleecker.reddit_demo.mvp.views.adapters.scrollers

import android.support.v7.widget.LinearLayoutManager
import timber.log.Timber

/**
 * @author Simulan
 * @version 1.0.0
 * @since 29/06/2017
 */

/**
 * InfiniteScrollListener which doenst keep an infinite list in memory
 * requires loading prepending items on demand
 * links to an adapter with a limited collection
 */
abstract class BufferedInfiniteScrollListener(layoutManager: LinearLayoutManager,private val VISIBLE_THRESHOLD : Int = 10) : InfiniteScrollListener(layoutManager,VISIBLE_THRESHOLD) {
    abstract var isBlockingPrepend: Boolean

    override fun onScroll(firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        Timber.v("Scrolling($totalItemCount) {loading:$loading} ")
        super.onScroll(firstVisibleItem, visibleItemCount, totalItemCount)
        val scrollingToStart : Boolean = (firstVisibleItem - VISIBLE_THRESHOLD) <= 0
        if(!loading && !isBlockingPrepend  && scrollingToStart) onPrependItems()
    }
    fun adapterStoppedLoading() {
        loading = false
    }
    abstract fun onPrependItems(): Boolean
}