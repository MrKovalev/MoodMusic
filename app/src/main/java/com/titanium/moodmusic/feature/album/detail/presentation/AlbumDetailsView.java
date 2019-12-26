package com.titanium.moodmusic.feature.album.detail.presentation;

import com.titanium.moodmusic.component.presentation.BaseView;
import com.titanium.moodmusic.shared.tracks.Track;

import java.util.List;

import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface AlbumDetailsView extends BaseView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showTracks(List<Track> tracks);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setAlbumTitle(String title);
}
