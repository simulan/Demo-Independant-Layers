package be.simulan.reddit_demo.mvp.views

import be.simulan.reddit_demo.mvp.models.data.ThumbnailOverlay

interface ThreadsView : BaseView {
    fun showThumbnail(thumbnailOverlay: ThumbnailOverlay)
    fun showThread(id: String)
}