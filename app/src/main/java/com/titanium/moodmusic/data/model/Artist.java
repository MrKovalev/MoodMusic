package com.titanium.moodmusic.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artist {
    @SerializedName("mbid")
    private String mbid; //The musicbrainz id for the artist
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private List<ImageItem> images;
    @SerializedName("streamable")
    private String streamable;
    @SerializedName("playcount")
    private String playCount;
    @SerializedName("url")
    private String url;

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImageItem> getImages() {
        return images;
    }

    public void setImages(List<ImageItem> images) {
        this.images = images;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl(){
        if (getImages() != null && !images.isEmpty()){
            for (ImageItem imageItem : images){
                if (imageItem.getSize().equalsIgnoreCase("large")){
                    return imageItem.getUrl();
                }
            }
        }

        return null;
    }
}
