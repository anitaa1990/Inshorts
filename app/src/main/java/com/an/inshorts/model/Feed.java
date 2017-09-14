package com.an.inshorts.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Feed implements Serializable {

    @SerializedName("ID")
    private Long id;

    @SerializedName("TITLE")
    private String title;

    @SerializedName("URL")
    private String url;

    @SerializedName("PUBLISHER")
    private String publisher;

    @SerializedName("CATEGORY")
    private String category;

    @SerializedName("HOSTNAME")
    private String hostname;

    @SerializedName("TIMESTAMP")
    private Long timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
