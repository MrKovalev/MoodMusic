package com.titanium.moodmusic.ui.fragments.favoriteAlbumTracks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.ui.adapters.FavoriteAlbumTracksAdapter;
import com.titanium.moodmusic.ui.fragments.BaseFragment;

public class FavoriteAlbumTracksFragment extends BaseFragment {

    private FavoriteAlbumTracksAdapter favoriteAlbumTracksAdapter;
    private RecyclerView recyclerViewTracks;

    public static FavoriteAlbumTracksFragment newInstance() {
        Bundle args = new Bundle();
        FavoriteAlbumTracksFragment fragment = new FavoriteAlbumTracksFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favoriteAlbumTracksAdapter = new FavoriteAlbumTracksAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_album_tracks_fragment, container, false);
        recyclerViewTracks = view.findViewById(R.id.recycler_tracks_album);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewTracks.setLayoutManager(linearLayoutManager);
        recyclerViewTracks.setAdapter(favoriteAlbumTracksAdapter);

        return view;
    }



    @Override
    protected void search(String query) {

    }
}
