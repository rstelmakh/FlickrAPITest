package com.flickrapitest.network.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by roman on 1/21/2015.
 */
public class Photo {

    @Expose(serialize = true)
    @SerializedName("title")
    private String title;

    @Expose(serialize = true)
    @SerializedName("tags")
    private String tags;

    @Expose(serialize = true)
    @SerializedName("url_z")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
