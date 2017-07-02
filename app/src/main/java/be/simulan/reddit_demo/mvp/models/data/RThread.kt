package be.simulan.reddit_demo.mvp.models.data

/**
 * @author Simulan
 * @version 1.0.0
 * @since 13/06/2017
 */
/*
*                    var text: String = "",
                   var ref: String = "",7
                   var origin: String = "",
*
* */
class RThread(var id: String = "",
                   var title: String = "",
                   var author: String = "",
                   var score : Long = 0,
                   var thumbnail: Thumbnail = RThread.Thumbnail()) : Comparable<RThread> {
    class Thumbnail(var dimensions: Pair<Int, Int> = Pair(0, 0), var url: String = "")

    override fun compareTo(other: RThread): Int {
        return title.compareTo(other.title)
    }
}

