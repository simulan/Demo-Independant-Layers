package be.simulan.reddit_demo.mvp.views.adapters.viewholders

interface ThreadVHListener {
    fun isThumbnailClicked(): Boolean
    fun getThreadId(): String
}