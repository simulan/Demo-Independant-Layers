package be.sanderdebleecker.reddit_demo.mvp.views.adapters.scrollers


/**
 * @author Simulan
 * @version 1.0.0
 * @since 16/06/2017
 */

abstract class InfiniteScrollListener constructor(private val linearLayoutManager: android.support.v7.widget.LinearLayoutManager, private val VISIBLE_THRESHOLD: Int = 10) : android.support.v7.widget.RecyclerView.OnScrollListener() {
    abstract var isBlockingAppend: Boolean
    private var prevTotalItemCount : Int = 0
    protected var loading : Boolean = true


    override fun onScrolled(recyclerView: android.support.v7.widget.RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        onScroll(linearLayoutManager.findFirstVisibleItemPosition(),recyclerView!!.childCount,linearLayoutManager.itemCount)
    }

    open fun onScroll(firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        // reset when invalid
        if (totalItemCount < prevTotalItemCount) {
            this.prevTotalItemCount = totalItemCount
            if (totalItemCount == 0) { this.loading = true; }
        }
        // if the total item count increased we are done with loading
        if (loading && (totalItemCount > prevTotalItemCount)) {
            loading = false
            prevTotalItemCount = totalItemCount
        }
        //  load more if we re viewing the threshold's items, enabling smooth scrolling
        val scrollingToEnd : Boolean = (firstVisibleItem + visibleItemCount + VISIBLE_THRESHOLD) >= totalItemCount
        if (!loading && !isBlockingAppend && scrollingToEnd) loading = onAppendItems()
    }


// abstract fun onPrependItems() : Boolean

    abstract fun onAppendItems() : Boolean
}