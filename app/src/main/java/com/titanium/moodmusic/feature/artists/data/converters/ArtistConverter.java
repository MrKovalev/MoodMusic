package com.titanium.moodmusic.feature.artists.data.converters;

import com.titanium.moodmusic.component.converter.Converter;
import com.titanium.moodmusic.feature.artists.data.model.ArtistModel;
import com.titanium.moodmusic.feature.artists.data.model.ImageModel;
import com.titanium.moodmusic.feature.artists.domain.entities.Artist;
import com.titanium.moodmusic.feature.artists.domain.entities.Image;

import java.util.ArrayList;

import javax.inject.Inject;

public class ArtistConverter implements Converter<ArtistModel, Artist> {

    private final Converter<ImageModel, Image> imageConverter;

    @Inject
    ArtistConverter(ImageConverter imageConverter) {
        this.imageConverter = imageConverter;
    }

    @Override
    public Artist convert(ArtistModel from) {
        ArrayList<Image> images = new ArrayList<>();

        for (ImageModel imageModel : from.getImages()) {
            Image image = imageConverter.convert(imageModel);
            images.add(image);
        }

        return new Artist(
                from.getMbid(),
                from.getName(),
                images,
                from.getStreamable(),
                from.getPlayCount(),
                from.getUrl()
        );
    }
}
