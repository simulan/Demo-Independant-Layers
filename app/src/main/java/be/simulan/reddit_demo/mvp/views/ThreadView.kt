package be.simulan.reddit_demo.mvp.views

import be.simulan.reddit_demo.mvp.models.data.Comment
import be.simulan.reddit_demo.mvp.models.data.ThreadItem

interface ThreadView : BaseView {
    fun showThread(thread : ThreadItem)
    fun showComments(comments : List<Comment>)
}