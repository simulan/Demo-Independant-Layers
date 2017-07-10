package be.simulan.reddit_demo.mvp.views

import be.simulan.reddit_demo.mvp.models.data.ThreadHeader
import be.simulan.reddit_demo.mvp.models.data.ThumbnailOverlay

interface IThreadsView : IBaseView {
    fun showThreads(threads: List<ThreadHeader>)
    fun clearThreads()
    fun showThumbnail(thumbnailOverlay: ThumbnailOverlay)
}