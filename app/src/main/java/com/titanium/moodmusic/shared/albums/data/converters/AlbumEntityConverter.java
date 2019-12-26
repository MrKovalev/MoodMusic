package com.titanium.moodmusic.shared.albums.data.converters;

import com.titanium.moodmusic.component.converter.Converter;
import com.titanium.moodmusic.component.database.entity.AlbumEntity;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;

import javax.inject.Inject;

public class AlbumEntityConverter implements Converter<AlbumEntity, Album> {

    @Inject
    AlbumEntityConverter() {
    }

    @Override
    public Album convert(AlbumEntity from) {
        return new Album(
                from.getIdAlbum(),
                from.getNameAlbum(),
                from.getDescription(),
                from.getTracks()
        );
    }
}
