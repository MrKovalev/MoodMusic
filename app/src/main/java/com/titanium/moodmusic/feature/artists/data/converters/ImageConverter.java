package com.titanium.moodmusic.feature.artists.data.converters;

import com.titanium.moodmusic.component.converter.Converter;
import com.titanium.moodmusic.feature.artists.data.model.ImageModel;
import com.titanium.moodmusic.feature.artists.domain.entities.Image;

import javax.inject.Inject;

public class ImageConverter implements Converter<ImageModel, Image> {

    @Inject
    ImageConverter() {
    }

    @Override
    public Image convert(ImageModel from) {
        return new Image(from.getUrl(), from.getSize());
    }
}
