package com.titanium.moodmusic.feature.albums.presentation;

import com.titanium.moodmusic.component.presentation.BaseView;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
import com.titanium.moodmusic.shared.error.Message;

import java.util.List;

import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SingleStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface AlbumsView extends BaseView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showAlbums(List<Album> albumList);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showAddNewAlbumDialog();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showChooseActionDialog(Album album, int position);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showRenameAlbumDialog(Album album);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void addAlbum(Album album);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void editAlbum(Album album);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void deleteAlbum(int position);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openAlbumDetails(Album album);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showError(Message message);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgress();
}
