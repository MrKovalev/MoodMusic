package com.titanium.moodmusic.feature.album.detail.presentation;

import com.titanium.moodmusic.component.presentation.BasePresenter;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
import com.titanium.moodmusic.shared.tracks.Track;

import javax.inject.Inject;

import moxy.InjectViewState;

@InjectViewState
public class AlbumDetailsPresenter extends BasePresenter<AlbumDetailsView> {

    private final Album album;

    @Inject
    public AlbumDetailsPresenter(Album album) {
        this.album = album;
    }

    @Override
    protected void onFirstViewAttach() {
        getViewState().setAlbumTitle(album.getNameAlbum());
        getViewState().showTracks(album.getTracks());
    }
}