package be.simulan.reddit_demo.mvp.views

import be.simulan.reddit_demo.mvp.models.data.ThreadHeader

interface ThreadsView : BaseView {
    fun showThreads(threads: List<ThreadHeader>)
    fun clearThreads()
}