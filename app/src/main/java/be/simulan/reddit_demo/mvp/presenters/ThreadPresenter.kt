package be.simulan.reddit_demo.mvp.presenters

interface ThreadPresenter {
    fun loadThread(id:String)
    fun loadComments(id:String)
}