package be.simulan.reddit_demo.mvp.views

import be.simulan.reddit_demo.mvp.models.data.RThread

interface ThreadsView : BaseView {
    fun showThreads(threads: List<RThread>)
    fun clearThreads()
}