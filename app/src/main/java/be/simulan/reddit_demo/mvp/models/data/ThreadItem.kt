package be.simulan.reddit_demo.mvp.models.data



class ThreadItem(var id: String = "",
                 var title: String = "",
                 var author: String = "",
                 var score : Long = 0,
                 var type : Type = Type.Post,
                 var thumbnail: Thumbnail = Thumbnail()) : Comparable<ThreadItem> {
    enum class Type {
        Post,
        Link,
        Image,
        Video
    }
    override fun compareTo(other: ThreadItem): Int {
        return title.compareTo(other.title)
    }
}

