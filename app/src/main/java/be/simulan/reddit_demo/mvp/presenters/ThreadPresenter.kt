package be.simulan.reddit_demo.mvp.presenters

import be.simulan.reddit_demo.mvp.models.data.Comment
import be.simulan.reddit_demo.mvp.models.data.Thread

interface ThreadPresenter {
    fun loadThread(id:String)
    fun loadComments(id:String)
}