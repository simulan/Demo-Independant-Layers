
package be.sanderdebleecker.reddit_demo.mvp.models.json.threads_envelope_subtypes;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Media {

    @SerializedName("oembed")
    private Oembed mOembed;
    @SerializedName("type")
    private String mType;

    public Oembed getOembed() {
        return mOembed;
    }

    public void setOembed(Oembed oembed) {
        mOembed = oembed;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
