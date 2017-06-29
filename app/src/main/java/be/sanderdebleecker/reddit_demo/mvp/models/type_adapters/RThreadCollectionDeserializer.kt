package be.sanderdebleecker.reddit_demo.mvp.models.type_adapters

import be.sanderdebleecker.reddit_demo.mvp.models.data.RThread
import com.google.gson.*
import java.lang.reflect.Type

/**
 * @author Simulan
 * @version 1.0.0
 * @since 28/06/2017
 */
class RThreadCollectionDeserializer : JsonDeserializer<Array<RThread>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Array<RThread> {
        //TODO : prevent instantiating a Gson instance every response
        val dirtyInceptionGson = GsonBuilder().registerTypeAdapter(RThread::class.java,RThreadDeserializer()).create()
        val children = (json as JsonObject).getAsJsonObject("data").getAsJsonArray("children")
        val threads : Array<RThread> = Array(children.size(), {
            index -> dirtyInceptionGson.fromJson(children.get(index),RThread::class.java)
        })
        return threads
    }
}

