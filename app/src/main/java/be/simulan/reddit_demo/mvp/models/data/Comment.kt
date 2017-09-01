package be.simulan.reddit_demo.mvp.models.data

class Comment(var childs : MutableList<Comment>) {
    var text : String = ""
    var author : String = ""
}