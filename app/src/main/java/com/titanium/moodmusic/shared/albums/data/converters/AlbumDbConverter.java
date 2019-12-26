package com.titanium.moodmusic.shared.albums.data.converters;

import com.titanium.moodmusic.component.converter.Converter;
import com.titanium.moodmusic.component.database.entity.AlbumEntity;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;

import javax.inject.Inject;

public class AlbumDbConverter implements Converter<Album, AlbumEntity> {

    @Inject
    AlbumDbConverter() {
    }

    @Override
    public AlbumEntity convert(Album from) {
        return new AlbumEntity(
                from.getAlbumId(),
                from.getNameAlbum(),
                from.getDescription(),
                from.getTracks()
        );
    }
}
