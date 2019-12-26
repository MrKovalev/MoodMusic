package com.titanium.moodmusic.shared.albums.data.converters;

import com.titanium.moodmusic.component.converter.ListConverter;
import com.titanium.moodmusic.component.database.entity.AlbumEntity;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AlbumDbListConverter implements ListConverter<Album, AlbumEntity> {

    private final AlbumDbConverter albumDbConverter;

    @Inject
    AlbumDbListConverter(AlbumDbConverter albumDbConverter) {
        this.albumDbConverter = albumDbConverter;
    }

    @Override
    public List<AlbumEntity> convert(List<Album> albums) {
        ArrayList<AlbumEntity> albumEntities = new ArrayList<>();

        for (Album album : albums) {
            AlbumEntity albumEntity = albumDbConverter.convert(album);
            albumEntities.add(albumEntity);
        }

        return albumEntities;
    }
}
