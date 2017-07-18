package be.simulan.reddit_demo.mvp.views

import be.simulan.reddit_demo.mvp.models.data.ThumbnailItem

interface ThreadsView : BaseView {
    fun showThumbnail(thumbnailItem: ThumbnailItem)
    fun showThread(id: String)
}