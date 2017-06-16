package be.sanderdebleecker.reddit_demo.mvp.models.data

/**
 * @author Simulan
 * @version 1.0.0
 * @since 13/06/2017
 */

data class RThread(var id: String = "",
                   var title: String = "",
                   var author: String = "",
                   var text: String = "",
                   var ref: String = "",
                   var origin: String = "", var thumbnail: Thumbnail = RThread.Thumbnail()) {
    class Thumbnail(var dimensions: Pair<Int, Int> = Pair(0, 0), var url: String = "")
}

