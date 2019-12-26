package com.titanium.moodmusic.feature.tracks.presentation;

import com.titanium.moodmusic.component.presentation.BaseView;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
import com.titanium.moodmusic.shared.error.Message;
import com.titanium.moodmusic.shared.tracks.Track;

import java.util.List;

import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface TracksView extends BaseView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showLoadingItem();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideLoadingItem();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showLoadedTracks(List<Track> tracks);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showSearchedTracks(List<Track> tracks);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showSearchedByArtistTracks(List<Track> tracks);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openTrackDetail(Track track);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void clearTracks();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showError(Message message);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showAddTrackDialog(Track selectedTrack, List<Album> albums);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showSuccessSavedTrack();
}