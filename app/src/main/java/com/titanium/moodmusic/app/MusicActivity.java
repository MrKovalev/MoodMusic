package com.titanium.moodmusic.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.titanium.moodmusic.R;
import com.titanium.moodmusic.app.di.DaggerMusicActivityComponent;
import com.titanium.moodmusic.app.di.MusicActivityAdapterModule;
import com.titanium.moodmusic.app.utils.RateUtils;
import com.titanium.moodmusic.app.utils.ShareUtils;
import com.titanium.moodmusic.feature.albums.data.FavoriteAlbum;
import com.titanium.moodmusic.feature.tracks.data.model.Track;
import com.titanium.moodmusic.component.ui.MainPagerAdapter;
import com.titanium.moodmusic.feature.artists.ui.ArtistsFragment;
import com.titanium.moodmusic.feature.album.detail.ui.FavoriteAlbumTracksFragment;
import com.titanium.moodmusic.feature.albums.ui.FavoriteAlbumsFragment;
import com.titanium.moodmusic.feature.tracks.ui.TracksFragment;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicActivity extends AppCompatActivity
                implements ArtistsFragment.onFragmentInteractionListener,
        TracksFragment.onFragmentTrackGetAlbumsInteractionListener,
        TracksFragment.onFragmentTrackAddToAlbumInteractionListener,
        FavoriteAlbumTracksFragment.onFragmentTrackDeleteFromAlbumInteractionListener {

    @BindView(R.id.tl_main)
    TabLayout tableLayout;
    @BindView(R.id.vp_main)
    ViewPager viewPager;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @Inject
    MainPagerAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //инитим UI
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        //внедряем зависимости
        DaggerMusicActivityComponent.builder()
                .musicActivityAdapterModule(new MusicActivityAdapterModule(getSupportFragmentManager(),this))
                .build()
                .inject(this);

        setupToolbarAndNavigationMenu();
        initFragments();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.music_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_share:
                startActivity(ShareUtils.shareAppAction());
                break;
            case R.id.nav_rate:
                startActivity(RateUtils.findApplicationsForRate());
                break;
        }
        return false;
    }

    /** Вспомогательные локальные методы **/

    private void initFragments(){
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        tableLayout.setupWithViewPager(viewPager);
    }

    private void setupToolbarAndNavigationMenu(){
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(true);
    }


    /** Методы для взаимодействия между фрагментами **/

    @Override
    public void onFragmentArtistInteraction(Bundle data) {
        TracksFragment fragment = (TracksFragment) (mainPagerAdapter.getCreatedFragment(MainPagerAdapter.TRACKS_IDX)).get();

        if (fragment != null)
            fragment.setBundle(data);
    }

    @Override
    public List<FavoriteAlbum> onFragmentTrackGetAlbumsInteraction() {
        FavoriteAlbumsFragment favoriteAlbumsFragment = (FavoriteAlbumsFragment)
                (mainPagerAdapter.getCreatedFragment(MainPagerAdapter.FAVORITE_ALBUMS_IDX).get());

        if (favoriteAlbumsFragment != null){
            return favoriteAlbumsFragment.getAllAlbums();
        }

        return Collections.emptyList();
    }

    @Override
    public void onFragmentTrackAddToAlbumInteraction(Track track, int positionAlbum) {
        FavoriteAlbumsFragment favoriteAlbumsFragment = (FavoriteAlbumsFragment)
                (mainPagerAdapter.getCreatedFragment(MainPagerAdapter.FAVORITE_ALBUMS_IDX).get());

        if (favoriteAlbumsFragment != null)
            favoriteAlbumsFragment.addTrackToAlbum(track, positionAlbum);
    }

    @Override
    public void onFragmentTrackDeleteFromAlbumInteraction(Track track, int positionTrackInAlbum) {
        FavoriteAlbumsFragment favoriteAlbumsFragment = (FavoriteAlbumsFragment)
                (mainPagerAdapter.getCreatedFragment(MainPagerAdapter.FAVORITE_ALBUMS_IDX).get());

        if (favoriteAlbumsFragment != null)
            favoriteAlbumsFragment.deleteTrackFromAlbum(track, positionTrackInAlbum);
    }
}