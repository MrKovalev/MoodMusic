package com.titanium.moodmusic.feature.tracks.data.converters;

import com.titanium.moodmusic.component.converter.Converter;
import com.titanium.moodmusic.component.converter.ListConverter;
import com.titanium.moodmusic.feature.tracks.data.model.chart.ChartTrackModel;
import com.titanium.moodmusic.shared.tracks.Track;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ChartTrackListConverter implements ListConverter<ChartTrackModel, Track> {

    private Converter<ChartTrackModel, Track> trackConverter;

    @Inject
    ChartTrackListConverter(ChartTrackConverter trackListConverter) {
        this.trackConverter = trackListConverter;
    }

    @Override
    public List<Track> convert(List<ChartTrackModel> list) {
        ArrayList<Track> tracks = new ArrayList<>();

        for (ChartTrackModel model : list) {
            tracks.add(trackConverter.convert(model));
        }

        return tracks;
    }
}
