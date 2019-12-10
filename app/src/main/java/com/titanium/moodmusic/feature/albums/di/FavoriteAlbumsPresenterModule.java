package com.titanium.moodmusic.feature.albums.di;

import com.titanium.moodmusic.component.database.dao.MusicDao;
import com.titanium.moodmusic.di.RoomModule;
import com.titanium.moodmusic.feature.albums.domain.FavoriteAlbumsInteractor;
import com.titanium.moodmusic.feature.albums.presentation.FavoriteAlbumsPresenter;
import com.titanium.moodmusic.feature.albums.domain.IFavoriteAlbumsInteractor;
import com.titanium.moodmusic.feature.albums.presentation.IFavoriteAlbumsPresenter;
import com.titanium.moodmusic.feature.albums.presentation.IFavoriteAlbumsView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = RoomModule.class)
public class FavoriteAlbumsPresenterModule {

    IFavoriteAlbumsView iFavoriteAlbumsView;

    public FavoriteAlbumsPresenterModule(IFavoriteAlbumsView iFavoriteAlbumsView) {
        this.iFavoriteAlbumsView = iFavoriteAlbumsView;
    }

    @Singleton
    @Provides
    public IFavoriteAlbumsPresenter providesFavoriteAlbumsPresenter(IFavoriteAlbumsView iFavoriteAlbumsView
            , IFavoriteAlbumsInteractor iFavoriteAlbumsInteractor){
        return new FavoriteAlbumsPresenter(iFavoriteAlbumsView,iFavoriteAlbumsInteractor);
    }

    @Singleton
    @Provides
    public IFavoriteAlbumsView providesAlbumsView(){
        return this.iFavoriteAlbumsView;
    }

    @Singleton
    @Provides
    public IFavoriteAlbumsInteractor providesFavoriteAlbumsInteractor(MusicDao musicDao){
        return new FavoriteAlbumsInteractor(musicDao);
    }
}
