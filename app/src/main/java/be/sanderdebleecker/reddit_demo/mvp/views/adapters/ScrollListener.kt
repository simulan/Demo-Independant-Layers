package be.sanderdebleecker.reddit_demo.mvp.views.adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * @author Simulan
 * @version 1.0.0
 * @since 16/06/2017
 */
abstract class ScrollListener constructor(private val linearLayoutManager: LinearLayoutManager, private val visibleThreshold : Int = 5) : RecyclerView.OnScrollListener() {
    private val startingPage : Int = 0
    private var currPage : Int = 0
    private var prevTotalItemCount : Int = 0
    private var loading : Boolean = true


    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        onScroll(linearLayoutManager.findFirstVisibleItemPosition(),recyclerView!!.childCount,linearLayoutManager.itemCount)
    }

    fun onScroll(firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        // reset when invalid
        if (totalItemCount < prevTotalItemCount) {
            currPage = startingPage
            this.prevTotalItemCount = totalItemCount
            if (totalItemCount == 0) { this.loading = true; }
        }
        // if the total item count increased we are done with loading
        if (loading && (totalItemCount > prevTotalItemCount)) {
            loading = false
            prevTotalItemCount = totalItemCount
            currPage++
        }
        // load more if we re viewing the threshold's items, enabling smooth scrolling
        if (!loading && (firstVisibleItem + visibleItemCount + visibleThreshold) >= totalItemCount ) {
            loading = onLoadMore(currPage + 1, totalItemCount)
        }
    }
    abstract fun onLoadMore(page : Int,totalItemCount: Int) : Boolean
}