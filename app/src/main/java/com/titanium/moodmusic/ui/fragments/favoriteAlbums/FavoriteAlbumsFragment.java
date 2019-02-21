package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.ui.fragments.BaseFragment;

public class FavoriteAlbumsFragment extends BaseFragment {

    private RecyclerView tracksRecyclerView;
    private ProgressBar progressBarMain;
    private View emptyLayout;

    public FavoriteAlbumsFragment(){}

    public static FavoriteAlbumsFragment newInstance(){
        return new FavoriteAlbumsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_albums_fragment, container, false);
        tracksRecyclerView = view.findViewById(R.id.recycler_favorite_albums);
        progressBarMain = view.findViewById(R.id.progress_favorite_albums);
        emptyLayout = view.findViewById(R.id.empty_layout_favorite);
        return view;
    }

    @Override
    protected void search(String query) {

    }
}
