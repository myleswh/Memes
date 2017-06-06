package dankmemes.myleswh.dankmemes.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/** Generated http://www.jsonschema2pojo.org/ */
public class GalleryModel {

    @SerializedName("data")
    @Expose
    public List<Datum> data = new ArrayList<Datum>();
    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("status")
    @Expose
    public Integer status;

    public class Datum {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("description")
        @Expose
        public Object description;
        @SerializedName("datetime")
        @Expose
        public Integer datetime;
        @SerializedName("cover")
        @Expose
        public String cover;
        @SerializedName("cover_width")
        @Expose
        public Integer coverWidth;
        @SerializedName("cover_height")
        @Expose
        public Integer coverHeight;
        @SerializedName("account_url")
        @Expose
        public String accountUrl;
        @SerializedName("account_id")
        @Expose
        public Integer accountId;
        @SerializedName("privacy")
        @Expose
        public String privacy;
        @SerializedName("layout")
        @Expose
        public String layout;
        @SerializedName("views")
        @Expose
        public Integer views;
        @SerializedName("link")
        @Expose
        public String link;
        @SerializedName("ups")
        @Expose
        public Integer ups;
        @SerializedName("downs")
        @Expose
        public Integer downs;
        @SerializedName("points")
        @Expose
        public Integer points;
        @SerializedName("score")
        @Expose
        public Integer score;
        @SerializedName("is_album")
        @Expose
        public Boolean isAlbum;
        @SerializedName("vote")
        @Expose
        public Object vote;
        @SerializedName("favorite")
        @Expose
        public Boolean favorite;
        @SerializedName("nsfw")
        @Expose
        public Boolean nsfw;
        @SerializedName("section")
        @Expose
        public String section;
        @SerializedName("comment_count")
        @Expose
        public Integer commentCount;
        @SerializedName("comment_preview")
        @Expose
        public Object commentPreview;
        @SerializedName("topic")
        @Expose
        public String topic;
        @SerializedName("topic_id")
        @Expose
        public Integer topicId;
        @SerializedName("images_count")
        @Expose
        public Integer imagesCount;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("animated")
        @Expose
        public Boolean animated;
        @SerializedName("width")
        @Expose
        public Integer width;
        @SerializedName("height")
        @Expose
        public Integer height;
        @SerializedName("size")
        @Expose
        public Integer size;
        @SerializedName("bandwidth")
        @Expose
        public String bandwidth;
        @SerializedName("gifv")
        @Expose
        public String gifv;
        @SerializedName("webm")
        @Expose
        public String webm;
        @SerializedName("mp4")
        @Expose
        public String mp4;
        @SerializedName("looping")
        @Expose
        public Boolean looping;

    }

}
