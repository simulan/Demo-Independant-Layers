package be.simulan.reddit_demo.mvp.models.data

class ThreadHeader(var id: String = "",
                   var title: String = "",
                   var author: String = "",
                   var score : Long = 0,
                   var thumbnail: Thumbnail = ThreadHeader.Thumbnail()) : Comparable<ThreadHeader> {
    class Thumbnail(var dimensions: Pair<Int, Int> = Pair(0, 0), var url: String = "")

    override fun compareTo(other: ThreadHeader): Int {
        return title.compareTo(other.title)
    }
}

