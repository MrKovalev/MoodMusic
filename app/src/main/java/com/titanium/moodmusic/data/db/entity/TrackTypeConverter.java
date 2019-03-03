package com.titanium.moodmusic.data.db.entity;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.titanium.moodmusic.data.model.tracks.Track;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class TrackTypeConverter {

    @TypeConverter
    public static List<Track> stringToTrackList(String data){
        if (data == null){
            return Collections.emptyList();
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Track>>(){}.getType();
        return gson.fromJson(data,type);
    }

    @TypeConverter
    public static String trackListToString(List<Track> trackListData){
        if (trackListData == null){
             return "None";
        }

        Gson gson = new Gson();
        return gson.toJson(trackListData);
    }
}
