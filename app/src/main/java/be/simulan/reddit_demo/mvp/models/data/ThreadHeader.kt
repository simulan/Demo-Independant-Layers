package be.simulan.reddit_demo.mvp.models.data

class ThreadHeader(var id: String = "",
                   var title: String = "",
                   var author: String = "",
                   var score : Long = 0,
                   var thumbnail: Thumbnail = Thumbnail()) : Comparable<ThreadHeader> {

    override fun compareTo(other: ThreadHeader): Int {
        return title.compareTo(other.title)
    }
}

