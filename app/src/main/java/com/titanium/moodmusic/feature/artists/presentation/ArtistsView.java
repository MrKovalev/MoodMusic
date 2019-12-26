package com.titanium.moodmusic.feature.artists.presentation;

import com.titanium.moodmusic.component.presentation.BaseView;
import com.titanium.moodmusic.feature.artists.domain.entities.Artist;
import com.titanium.moodmusic.shared.error.Message;

import java.util.List;

import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface ArtistsView extends BaseView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showLoadingItem();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideLoadingItem();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showChartArtists(List<Artist> artists);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showSearchedArtists(List<Artist> artists);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void clearArtists();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showError(Message message);
}
