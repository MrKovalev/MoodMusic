package com.titanium.moodmusic.ui.fragments.tracks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.data.api.Constants;
import com.titanium.moodmusic.data.api.retrofit.LastFmRetrofitClient;
import com.titanium.moodmusic.data.model.tracks.Track;
import com.titanium.moodmusic.ui.adapters.TracksAdapter;
import com.titanium.moodmusic.ui.fragments.BaseFragment;
import com.titanium.moodmusic.ui.fragments.trackDetail.TrackDetailFragment;

import java.util.List;

public class TracksFragment extends BaseFragment
        implements ITracksView, SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private RecyclerView tracksRecyclerView;
    private SearchView searchView;
    private ProgressBar progressBarMain;
    private View emptyLayout;

    private ITracksPresenter tracksPresenter;
    private TracksAdapter tracksAdapter;

    private boolean isLoading, isSearchingNow;
    private int currentPageTopChartTracks = Constants.DEFAULT_PAGE_CHART;
    private int currentPageSearch = Constants.DEFAULT_PAGE_CHART;

    public TracksFragment(){}

    public static TracksFragment newInstance(){
        return new TracksFragment();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (this.isVisible() && this.getClass() == TracksFragment.class){
            Log.d("TAG","LOADING TRACKS");


            tracksPresenter.searchTracksByArtist("disturbed",""
                    ,Constants.TOP_ITEMS_LIMIT_CHART,Constants.DEFAULT_PAGE_CHART,Constants.API_KEY);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        tracksPresenter = new TracksPresenter(this,new TracksInteractor(LastFmRetrofitClient.getInstance().getLastFmApi()));
        tracksAdapter = new TracksAdapter(getContext());
        tracksAdapter.setTrackItemClickListener(new TracksAdapter.ItemTrackClickListener() {
            @Override
            public void onItemClick(List<Track> trackList, Track track, int position) {
                tracksPresenter.openTrackDetail(trackList,track,position);
            }
        });
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();

        if(this.isVisible()){
            getActivity().getMenuInflater().inflate(R.menu.music_toolbar_menu, menu);
            MenuItem menuItem = menu.findItem(R.id.search_field);

            if (menuItem != null){
                searchView = (SearchView) menuItem.getActionView();
                searchView.setOnQueryTextListener(this);
                searchView.setOnCloseListener(this);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tracks_fragment, container, false);
        tracksRecyclerView = view.findViewById(R.id.recycler_tracks);
        progressBarMain = view.findViewById(R.id.progress_tracks);
        emptyLayout = view.findViewById(R.id.empty_layout_tracks);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        tracksRecyclerView.setLayoutManager(linearLayoutManager);
        tracksRecyclerView.setAdapter(tracksAdapter);
        tracksRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0){
                    if(!isSearchingNow){
                        int totalItemCount = linearLayoutManager.getItemCount();
                        int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                        if (!isLoading){
                            if ((totalItemCount - 1) == lastVisibleItem) {
                                currentPageTopChartTracks++;
                                isLoading = true;
                                tracksPresenter.getTopChartTracks(currentPageTopChartTracks,Constants.TOP_ITEMS_LIMIT_CHART,Constants.API_KEY);
                            }
                        }
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //tracksPresenter.getTopChartTracks(Constants.DEFAULT_PAGE_SEARCH,Constants.TOP_ITEMS_LIMIT_CHART,Constants.API_KEY);
    }

    @Override
    protected void search(String query) {
        isSearchingNow = true;
        tracksPresenter.searchTrack(Constants.TOP_ITEMS_LIMIT_SEARCH,
                Constants.DEFAULT_PAGE_SEARCH,query, null, Constants.API_KEY);
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
    public void loadTracks(List<Track> trackList) {
        if (tracksAdapter != null){
            isLoading = false;
            tracksAdapter.setTrackList(trackList);
        }
    }

    @Override
    public void searchTracks(List<Track> trackList) {
        if (tracksAdapter != null){
            tracksAdapter.clearArtistList();
            tracksAdapter.setTrackList(trackList);
        }
    }

    @Override
    public void searchTracksByArtist(List<Track> trackList) {
        if (tracksAdapter != null){
            tracksAdapter.clearArtistList();
            tracksAdapter.setTrackList(trackList);
        }
    }

    @Override
    public void openTrackDetail(List<Track> trackList, Track track, int position) {
        TrackDetailFragment.newInstance(null,position)
                .show(getFragmentManager(),"");
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

    @Override
    public boolean onClose() {
        tracksPresenter.getTopChartTracks(Constants.DEFAULT_PAGE_CHART,Constants.TOP_ITEMS_LIMIT_CHART,Constants.API_KEY);
        isSearchingNow = false;
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        search(newText);
        return false;
    }
}
