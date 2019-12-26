package com.titanium.moodmusic.feature.tracks.data.converters;

import com.titanium.moodmusic.component.converter.Converter;
import com.titanium.moodmusic.feature.tracks.data.model.chart.ChartTrackModel;
import com.titanium.moodmusic.shared.tracks.Track;

import javax.inject.Inject;

public class ChartTrackConverter implements Converter<ChartTrackModel, Track> {

    @Inject
    ChartTrackConverter() {
    }

    @Override
    public Track convert(ChartTrackModel from) {
        return new Track(
                from.getMbid(),
                from.getName(),
                from.getPlayCount(),
                from.getUrl(),
                from.getArtist().getMbid(),
                from.getArtist().getName(),
                from.getArtist().getUrl()
        );
    }
}
