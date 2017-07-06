package be.simulan.reddit_demo.mvp.models.type_adapters

import be.simulan.reddit_demo.mvp.models.data.ThreadHeader
import com.google.gson.*
import java.lang.reflect.Type

/**
 * @author Simulan
 * @version 1.0.0
 * @since 28/06/2017
 */
class RThreadCollectionDeserializer : JsonDeserializer<Array<ThreadHeader>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Array<ThreadHeader> {
        //TODO : prevent instantiating a Gson instance every response
        val dirtyInceptionGson = GsonBuilder().registerTypeAdapter(ThreadHeader::class.java,RThreadDeserializer()).create()
        val children = (json as JsonObject).getAsJsonObject("data").getAsJsonArray("children")
        val threads : Array<ThreadHeader> = Array(children.size(), {
            index -> dirtyInceptionGson.fromJson(children.get(index), ThreadHeader::class.java)
        })
        return threads
    }
}

