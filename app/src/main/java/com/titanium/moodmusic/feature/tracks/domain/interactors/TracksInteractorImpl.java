package com.titanium.moodmusic.feature.tracks.domain.interactors;

import com.titanium.moodmusic.feature.tracks.domain.repositories.TracksRepository;
import com.titanium.moodmusic.shared.tracks.Track;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;

public class TracksInteractorImpl implements TracksInteractor {

    private final TracksRepository tracksRepository;

    @Inject
    public TracksInteractorImpl(TracksRepository tracksRepository) {
        this.tracksRepository = tracksRepository;
    }

    @Override
    public Single<List<Track>> getTopChartTracks(int page, int limit) {
        return tracksRepository.getTopChartTracks(page, limit);
    }

    @Override
    public Single<List<Track>> searchTrack(int limit, int page, String nameTrack, String nameArtist) {
        return tracksRepository.searchTrack(limit, page, nameTrack, nameArtist)
                .flattenAsObservable(tracks -> tracks)
                .distinct()
                .toList();
    }

    @Override
    public Single<List<Track>> searchTracksByArtist(String nameArtist, String mbid, int limit, int page) {
        return tracksRepository.searchTracksByArtist(nameArtist, mbid, limit, page)
                .flattenAsObservable(tracks -> tracks)
                .distinct()
                .toList();
    }
}
