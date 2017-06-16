package be.sanderdebleecker.reddit_demo.mvp.models.json

import be.sanderdebleecker.reddit_demo.mvp.models.data.RThread
import be.sanderdebleecker.reddit_demo.mvp.models.json.threads_envelope_subtypes.Data
import com.google.gson.annotations.SerializedName

class ThreadsEnvelope {
    @SerializedName("data")
    var data: Data? = null
    @SerializedName("kind")
    var kind: String? = null

    fun viewable(): MutableList<RThread> {
        var threads: MutableList<RThread> = ArrayList()
        data?.children?.forEach {
            var thread: RThread = RThread()

            thread.id = it.data!!.id ?: ""
            thread.title = it.data!!.title ?:  ""
            thread.author = it.data!!.author ?:  ""
            thread.text = it.data!!.selftext ?:  ""
            thread.origin = it.data!!.permalink ?:  ""
            thread.ref = it.data!!.url ?:  ""

            thread.thumbnail = RThread.Thumbnail(Pair(it.data!!.thumbnailWidth ?:0, it.data!!.thumbnailHeight ?:0), it.data!!.thumbnail ?: "")
            threads.add(thread)
        }
        return threads

    }
}
