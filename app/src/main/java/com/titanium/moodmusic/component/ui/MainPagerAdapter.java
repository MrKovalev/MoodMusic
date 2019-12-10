package com.titanium.moodmusic.component.ui;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.feature.artists.ui.ArtistsFragment;
import com.titanium.moodmusic.feature.albums.ui.FavoriteAlbumsFragment;
import com.titanium.moodmusic.feature.tracks.ui.TracksFragment;

import java.lang.ref.WeakReference;
import java.util.Hashtable;

public class MainPagerAdapter extends FragmentPagerAdapter {

    public static final int NUMBER_OF_ITEMS = 3;
    public static final int ARTISTS_IDX = 0;
    public static final int TRACKS_IDX = 1;
    public static final int FAVORITE_ALBUMS_IDX = 2;

    private String artistsTitle;
    private String tracksTitle;
    private String favoriteAlbumsTitle;

    protected Hashtable<Integer, WeakReference<Fragment>> fragments;

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        artistsTitle = context.getString(R.string.artists_title);
        tracksTitle = context.getString(R.string.tracks_title);
        favoriteAlbumsTitle = context.getString(R.string.favorite_albums_title);
        fragments = new Hashtable<>();
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case ARTISTS_IDX:{
                Fragment fr = ArtistsFragment.newInstance();
                fragments.put(i,new WeakReference<Fragment>(fr));
                return fr;
            }
            case TRACKS_IDX:{
                Fragment fr = TracksFragment.newInstance();
                fragments.put(i,new WeakReference<Fragment>(fr));
                return fr;
            }
            case FAVORITE_ALBUMS_IDX:{
                Fragment fr = FavoriteAlbumsFragment.newInstance();
                fragments.put(i,new WeakReference<Fragment>(fr));
                return fr;
            }
        }

        return new ArtistsFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case ARTISTS_IDX:
                return artistsTitle;
            case TRACKS_IDX:
                return tracksTitle;
            case FAVORITE_ALBUMS_IDX:
                return favoriteAlbumsTitle;
        }

        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_ITEMS;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        fragments.remove(position);
    }

    public WeakReference<Fragment> getCreatedFragment(int position){
        return fragments.get(position);
    }
}
