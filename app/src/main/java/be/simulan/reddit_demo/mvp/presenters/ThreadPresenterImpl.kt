package be.simulan.reddit_demo.mvp.presenters

import be.simulan.reddit_demo.mvp.models.data.Comment
import be.simulan.reddit_demo.mvp.models.data.Thread

class ThreadPresenterImpl : ThreadPresenter {
    override fun loadThread() {

    }
    override fun loadComments() {

    }
    override fun getThread() : Thread {
        return Thread()

    }
    override fun getComments(): ArrayList<Comment> {
        return ArrayList<Comment>()
    }
}