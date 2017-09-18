package be.simulan.reddit_demo.mvp.models.type_adapters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

import be.simulan.reddit_demo.mvp.models.data.Comment;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static be.simulan.reddit_demo.mvp.models.type_adapters.utils.CommentDeserializerUtilKt.getComment;

public class BoxedJsonArrayConverter implements Converter<ResponseBody,Comment[]> {
    private Gson gson;

    BoxedJsonArrayConverter () {
        gson = new Gson();
    }
    @Override public Comment[] convert (ResponseBody value) throws IOException {
        String json = value.string();
        JsonArray rootArray = gson.fromJson(json, JsonArray.class);
        if(hasCommentsObject(rootArray)) {
            int commentsIndex = 1;
            return getComments(rootArray.get(commentsIndex).getAsJsonObject());
        }else{
            return getEmptyCommentArray();
        }
    }
    private boolean hasCommentsObject (JsonArray rootArray) {
        return rootArray.size()>1;
    }
    private Comment[] getEmptyCommentArray() {
        return new Comment[0];
    }
    private Comment[] getComments(JsonObject json) {
        JsonArray commentsArray = json.get("data").getAsJsonObject().get("children").getAsJsonArray();
        Comment[] results = new Comment[commentsArray.size()];
        for(int i=0;i<commentsArray.size();i++) {
            JsonElement jsonElement = commentsArray.iterator().next();
            results[i] = getComment(jsonElement.getAsJsonObject().get("data").getAsJsonObject());
        }
        return results;
    }

}
