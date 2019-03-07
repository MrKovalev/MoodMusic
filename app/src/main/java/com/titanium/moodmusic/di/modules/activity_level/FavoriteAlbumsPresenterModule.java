package com.titanium.moodmusic.di.modules.activity_level;

import com.titanium.moodmusic.data.db.dao.MusicDao;
import com.titanium.moodmusic.di.modules.app_level.RoomModule;
import com.titanium.moodmusic.ui.fragments.favoriteAlbums.FavoriteAlbumsInteractor;
import com.titanium.moodmusic.ui.fragments.favoriteAlbums.FavoriteAlbumsPresenter;
import com.titanium.moodmusic.ui.fragments.favoriteAlbums.IFavoriteAlbumsInteractor;
import com.titanium.moodmusic.ui.fragments.favoriteAlbums.IFavoriteAlbumsPresenter;
import com.titanium.moodmusic.ui.fragments.favoriteAlbums.IFavoriteAlbumsView;

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
