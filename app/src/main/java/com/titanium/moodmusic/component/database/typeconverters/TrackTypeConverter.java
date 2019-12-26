package com.titanium.moodmusic.component.database.typeconverters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.titanium.moodmusic.shared.tracks.Track;

import java.lang.reflect.Type;
import java.util.List;

public class TrackTypeConverter {

    private final Gson gson = new GsonBuilder().create();

    @TypeConverter
    public List<Track> stringToTrackList(String data) {
        Type type = new TypeToken<List<Track>>() {}.getType();
        return gson.fromJson(data, type);
    }

    @TypeConverter
    public String trackListToString(List<Track> tracks) {
        return gson.toJson(tracks);
    }
}