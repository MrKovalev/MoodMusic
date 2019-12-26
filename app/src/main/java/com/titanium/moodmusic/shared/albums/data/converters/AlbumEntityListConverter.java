package com.titanium.moodmusic.shared.albums.data.converters;

import com.titanium.moodmusic.component.converter.ListConverter;
import com.titanium.moodmusic.component.database.entity.AlbumEntity;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AlbumEntityListConverter implements ListConverter<AlbumEntity, Album> {

    private final AlbumEntityConverter albumEntityConverter;

    @Inject
    AlbumEntityListConverter(AlbumEntityConverter albumEntityConverter) {
        this.albumEntityConverter = albumEntityConverter;
    }

    @Override
    public List<Album> convert(List<AlbumEntity> albumEntities) {
        ArrayList<Album> albums = new ArrayList<>();

        for (AlbumEntity albumEntity : albumEntities) {
            Album album = albumEntityConverter.convert(albumEntity);
            albums.add(album);
        }

        return albums;
    }
}
