package com.titanium.moodmusic.feature.artists.data.converters;

import com.titanium.moodmusic.component.converter.Converter;
import com.titanium.moodmusic.component.converter.ListConverter;
import com.titanium.moodmusic.feature.artists.data.model.ArtistModel;
import com.titanium.moodmusic.feature.artists.domain.entities.Artist;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ArtistListConverter implements ListConverter<ArtistModel, Artist> {

    private final Converter<ArtistModel, Artist> artistConverter;

    @Inject
    ArtistListConverter(ArtistConverter artistConverter) {
        this.artistConverter = artistConverter;
    }

    @Override
    public List<Artist> convert(List<ArtistModel> list) {
        ArrayList<Artist> artists = new ArrayList<>();

        for (ArtistModel model : list) {
            artists.add(artistConverter.convert(model));
        }

        return artists;
    }
}
