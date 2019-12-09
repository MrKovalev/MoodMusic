package com.titanium.moodmusic.data.model.tracks;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.data.model.artists.Artist;

import java.lang.reflect.Type;

public class Track {
    @SerializedName("mbid")
    private String mbid;
    @SerializedName("name")
    private String name;
    @SerializedName("playcount")
    private String playCount;
    @SerializedName("url")
    private String url;
    @SerializedName("artist")
    private Object artist;

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

    public Object getArtist() {
        return artist;
    }

    public void setArtist(Object artist) {
        this.artist = artist;
    }


    public static class ArtistStateDeserializer implements JsonDeserializer<Track>{
        @Override
        public Track deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Track track = new Gson().fromJson(json, Track.class);
            JsonObject jsonObject = json.getAsJsonObject();

            if (jsonObject.has("artist")){

                JsonElement element = jsonObject.get("artist");

                if (element != null && !element.isJsonNull()){
                    if (element.isJsonPrimitive()){
                        track.setArtist(element.getAsString());
                    } else {
                        Artist artist = new Artist();
                        artist.setName(element.getAsJsonObject().get("name").getAsString());
                        track.setArtist(artist);
                    }
                }
            }

            return track;
        }
    }

    @Override
    public String toString() {
        return "Track{" +
                "mbid='" + mbid + '\'' +
                ", name='" + name + '\'' +
                ", playCount='" + playCount + '\'' +
                ", url='" + url + '\'' +
                ", artist=" + artist +
                '}';
    }
}
