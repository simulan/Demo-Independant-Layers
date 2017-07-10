package be.simulan.reddit_demo.mvp.models.data

class ThreadItem(var id: String = "",
                 var title: String = "",
                 var author: String = "",
                 var score : Long = 0,
                 var thumbnail: Thumbnail = Thumbnail()) : Comparable<ThreadItem> {

    override fun compareTo(other: ThreadItem): Int {
        return title.compareTo(other.title)
    }
}

