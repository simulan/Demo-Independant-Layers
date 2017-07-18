package be.simulan.reddit_demo.mvp.models.type_adapters

import be.simulan.reddit_demo.AndroidTest
import be.simulan.reddit_demo.mvp.models.data.ThumbnailItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import org.junit.Test
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader

class ThumbnailItemDeserializerTest : AndroidTest() {
    val resourcesDirectory: String = "${System.getProperty("user.dir")}/src/main/res/raw/"
    val dummyFile = "example_thread_response.json"

    @Test
    fun deserialize() {
        val gson : Gson = GsonBuilder().registerTypeAdapter(ThumbnailItem::class.java,ThumbnailOverlayDeserializer()).create()
        val inputStream = mockContextOpenRawResource(dummyFile)
        val reader: Reader = InputStreamReader(inputStream)
        val thumbnailItem: ThumbnailItem = gson.fromJson(JsonReader(reader), ThumbnailItem::class.java)
        assert(thumbnailItem.title=="Synthetic Accessors in Kotlin")
    }

    fun mockContextOpenRawResource(fileName : String) : InputStream {
        return FileInputStream(resourcesDirectory +fileName)
    }

}