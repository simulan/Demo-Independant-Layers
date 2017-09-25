package be.simulan.reddit_demo.comments.models.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Iterator;

import be.simulan.reddit_demo.comments.models.Comment;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static be.simulan.reddit_demo.mvp.models.convert.utils.CommentDeserializerUtilKt.getComment;

public class BoxedCommentsConverter implements Converter<ResponseBody,Comment[]> {
    private Gson gson;

    BoxedCommentsConverter () {
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
        Iterator iterator = commentsArray.iterator();
        for(int i=0;i<commentsArray.size();i++) {
            JsonElement jsonElement = (JsonElement) iterator.next();
            results[i] = getComment(jsonElement.getAsJsonObject().get("data").getAsJsonObject());
        }
        return results;
    }

}
