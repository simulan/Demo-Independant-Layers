package be.simulan.reddit_demo.mvp.models.type_adapters

import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import be.simulan.reddit_demo.mvp.models.type_adapters.utils.asThread
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class QueriedThreadDeserializer : JsonDeserializer<ThreadItem> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ThreadItem {
        val jsonRoot = json!!.asJsonObject
        val jsonThread = jsonRoot["data"].asJsonObject["children"].asJsonArray[0].asJsonObject["data"].asJsonObject
        return jsonThread.asThread()
    }
}