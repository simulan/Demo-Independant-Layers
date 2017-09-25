package be.simulan.reddit_demo.thread.views

import be.simulan.reddit_demo.comments.models.Comment
import be.simulan.reddit_demo.thread.models.ThreadItem
import be.simulan.reddit_demo.application.views.BaseView

interface ThreadView : BaseView {
    fun showThread(thread : ThreadItem)
    fun showComments(comments : List<Comment>)
}