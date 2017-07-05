package be.simulan.reddit_demo.mvp.views.adapters.scrollers

abstract class EndlessScrollListener constructor(private val linearLayoutManager: android.support.v7.widget.LinearLayoutManager, private val VISIBLE_THRESHOLD: Int = 10) : android.support.v7.widget.RecyclerView.OnScrollListener() {
    private var previousItemCount: Int = 0
    protected var loading: Boolean = true

    override fun onScrolled(recyclerView: android.support.v7.widget.RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        onScroll(linearLayoutManager.findFirstVisibleItemPosition(),recyclerView!!.childCount,linearLayoutManager.itemCount)
    }
    fun onScroll(firstVisibleItem: Int, visibleItemCount: Int, itemCount: Int) {
        val scrollingToEnd : Boolean = (firstVisibleItem + visibleItemCount + VISIBLE_THRESHOLD) >= itemCount
        if (!totalItemCountIncreased(itemCount)) {
            this.previousItemCount = itemCount
            if (itemCount == 0) { this.loading = true; }
        }
        if (loading && totalItemCountIncreased(itemCount)) {
            loading = false
            previousItemCount = itemCount
        }
        if (!loading && scrollingToEnd) loading = onAppendItems()
    }
    private fun totalItemCountIncreased(totalItemCount: Int) : Boolean = totalItemCount > previousItemCount
    abstract fun onAppendItems() : Boolean
}