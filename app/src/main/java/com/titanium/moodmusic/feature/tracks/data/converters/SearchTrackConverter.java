package com.titanium.moodmusic.feature.tracks.data.converters;

import com.titanium.moodmusic.component.converter.Converter;
import com.titanium.moodmusic.feature.tracks.data.model.search.SearchTrackModel;
import com.titanium.moodmusic.shared.tracks.Track;

import javax.inject.Inject;

public class SearchTrackConverter implements Converter<SearchTrackModel, Track> {

    @Inject
    SearchTrackConverter() {
    }

    @Override
    public Track convert(SearchTrackModel from) {
        return new Track(
                from.getMbid(),
                from.getName(),
                from.getPlayCount(),
                from.getUrl(),
                "",
                from.getArtist(),
                ""
        );
    }
}
