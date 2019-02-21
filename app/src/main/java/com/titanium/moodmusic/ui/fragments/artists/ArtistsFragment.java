package com.titanium.moodmusic.ui.fragments.artists;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.data.api.retrofit.LastFmRetrofitClient;
import com.titanium.moodmusic.data.model.Artist;
import com.titanium.moodmusic.ui.adapters.ArtistsAdapter;
import com.titanium.moodmusic.ui.fragments.BaseFragment;

import java.util.List;

public class ArtistsFragment extends BaseFragment
            implements IArtistsView{

    private RecyclerView artistsRecyclerView;
    private ProgressBar progressBarMain;
    private OnFragmentInteractionListener listener;
    private View emptyLayout;

    private IArtistsPresenter artistsPresenter;
    private ArtistsAdapter artistsAdapter;

    public ArtistsFragment(){}

    public static ArtistsFragment newInstance(){
        return new ArtistsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        artistsPresenter = new ArtistsPresenter(this,new ArtistsInteractor(LastFmRetrofitClient.getInstance().getLastFmApi()));
        artistsAdapter = new ArtistsAdapter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artists_fragment, container, false);
        artistsRecyclerView = view.findViewById(R.id.recycler_artists);
        progressBarMain = view.findViewById(R.id.progress_artist);
        emptyLayout = view.findViewById(R.id.empty_layout_artists);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        artistsRecyclerView.setLayoutManager(linearLayoutManager);
        artistsRecyclerView.setAdapter(artistsAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        artistsPresenter.getTopChartArtists(10,"213");
    }

    @Override
    protected void search(String query) {

    }

    @Override
    public void showProgress() {
        progressBarMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBarMain.setVisibility(View.GONE);
    }

    @Override
    public void loadArtists(List<Artist> artistList) {
        if (artistsAdapter != null){
            Log.d("TAG","load data callback view");
            artistsAdapter.setArtistList(artistList);
            artistsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmpty() {
        emptyLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        emptyLayout.setVisibility(View.GONE);
    }


    public interface OnFragmentInteractionListener{

    }
}
