package be.simulan.reddit_demo.mvp.views

import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import be.simulan.reddit_demo.mvp.models.data.ThumbnailOverlay

interface ThreadsView : BaseView {
    fun showThreads(threads: List<ThreadItem>)
    fun clearThreads()
    fun showThumbnail(thumbnailOverlay: ThumbnailOverlay)
}