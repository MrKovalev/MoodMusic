package com.titanium.moodmusic.ui.fragments.tracks;

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

public class TracksFragment extends BaseFragment {

    private RecyclerView tracksRecyclerView;
    private ProgressBar progressBarMain;
    private View emptyLayout;

    public TracksFragment(){}

    public static TracksFragment newInstance(){
        return new TracksFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artists_fragment, container, false);
        tracksRecyclerView = view.findViewById(R.id.recycler_artists);
        progressBarMain = view.findViewById(R.id.progress_tracks);
        emptyLayout = view.findViewById(R.id.empty_layout_tracks);
        return view;
    }

    @Override
    protected void search(String query) {

    }
}
