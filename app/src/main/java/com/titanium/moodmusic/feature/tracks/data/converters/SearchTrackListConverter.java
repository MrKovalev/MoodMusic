package com.titanium.moodmusic.feature.tracks.data.converters;

import com.titanium.moodmusic.component.converter.Converter;
import com.titanium.moodmusic.component.converter.ListConverter;
import com.titanium.moodmusic.feature.tracks.data.model.search.SearchTrackModel;
import com.titanium.moodmusic.shared.tracks.Track;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchTrackListConverter implements ListConverter<SearchTrackModel, Track> {

    private Converter<SearchTrackModel, Track> trackConverter;

    @Inject
    SearchTrackListConverter(SearchTrackConverter trackListConverter) {
        this.trackConverter = trackListConverter;
    }

    @Override
    public List<Track> convert(List<SearchTrackModel> list) {
        ArrayList<Track> tracks = new ArrayList<>();

        for (SearchTrackModel model : list) {
            tracks.add(trackConverter.convert(model));
        }

        return tracks;
    }
}
