package be.simulan.reddit_demo.thread.views

import be.simulan.reddit_demo.thread.models.ThumbnailItem
import be.simulan.reddit_demo.application.views.BaseView

interface ThreadsView : BaseView {
    fun showThumbnail(thumbnailItem: ThumbnailItem)
    fun showThread(id: String)
}