package be.simulan.reddit_demo.mvp.models.type_adapters;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import be.simulan.reddit_demo.mvp.models.data.Comment;
import be.simulan.reddit_demo.mvp.models.data.enveloppe.CommentConversion;

public class CommentDeserializer2 implements JsonDeserializer<CommentConversion> {
    private String KIND_COMMENT = "t1";

    @Override
    public CommentConversion deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if(json.isJsonObject()) {
            JsonArray unboxedArray =  json.getAsJsonObject().get("data").getAsJsonObject().get("children").getAsJsonArray();
            Comment[] comments = new Comment[unboxedArray.size()];
            for(int i=0;unboxedArray.iterator().hasNext();i++){
                comments[i]=getComment(unboxedArray.iterator().next().getAsJsonObject());
            }
            return new CommentConversion(comments);
        } else {
            return null;
        }
    }

    private Comment getComment(JsonObject item) {
        if(isComment(item)){
            JsonObject commentData = item.get("data").getAsJsonObject();
            return getComment(commentData);
        }else{
            return null;
        }
    }

    private Boolean isComment(JsonObject jsonObject) {
        return jsonObject.get("kind").getAsString().equals(KIND_COMMENT);
    }


}
