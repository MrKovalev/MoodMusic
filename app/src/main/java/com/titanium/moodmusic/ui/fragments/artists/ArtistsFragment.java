package com.titanium.moodmusic.ui.fragments.artists;

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
import com.titanium.moodmusic.data.model.artists.Artist;
import com.titanium.moodmusic.ui.activities.MusicActivity;
import com.titanium.moodmusic.ui.adapters.ArtistsAdapter;
import com.titanium.moodmusic.ui.adapters.MainPagerAdapter;
import com.titanium.moodmusic.ui.fragments.BaseFragment;

import java.util.List;

public class ArtistsFragment extends BaseFragment
            implements IArtistsView, SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private RecyclerView artistsRecyclerView;
    private SearchView searchView;
    private ProgressBar progressBarMain;
    private OnFragmentInteractionListener listener;
    private View emptyLayout;

    private IArtistsPresenter artistsPresenter;
    private ArtistsAdapter artistsAdapter;

    private boolean isLoading, isSearchingNow;
    private int currentPageTopChartArtists = Constants.DEFAULT_PAGE_CHART;
    private int currentPageSearch = Constants.DEFAULT_PAGE_CHART;

    public ArtistsFragment(){}

    public static ArtistsFragment newInstance(){
        return new ArtistsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        artistsPresenter = new ArtistsPresenter(this,new ArtistsInteractor(LastFmRetrofitClient.getInstance().getLastFmApi()));
        artistsAdapter = new ArtistsAdapter(getContext());
        artistsAdapter.setArtistItemClickListener(new ArtistsAdapter.ItemArtistClickListener() {
            @Override
            public void onItemClick(Artist track) {

                //set flag to activity to update tracks fragment

                //switch to tracks
                MusicActivity activity = (MusicActivity) getActivity();

                if (activity.getViewPager() != null){
                    activity.getViewPager().setCurrentItem(MainPagerAdapter.TRACKS_IDX);
                }
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();

        if (this.isVisible()){
            getActivity().getMenuInflater().inflate(R.menu.music_toolbar_menu, menu);
            MenuItem searchItem = menu.findItem(R.id.search_field);

            if (searchItem != null){
                searchView = (SearchView) searchItem.getActionView();
                searchView.setOnQueryTextListener(this);
                searchView.setOnCloseListener(this);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artists_fragment, container, false);
        artistsRecyclerView = view.findViewById(R.id.recycler_artists);
        progressBarMain = view.findViewById(R.id.progress_artist);
        emptyLayout = view.findViewById(R.id.empty_layout_artists);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        artistsRecyclerView.setLayoutManager(linearLayoutManager);
        artistsRecyclerView.setAdapter(artistsAdapter);

        artistsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0){
                    if(!isSearchingNow){
                        int totalItemCount = linearLayoutManager.getItemCount();
                        int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                        if (!isLoading){
                            if ((totalItemCount - 1) == lastVisibleItem) {
                                currentPageTopChartArtists++;
                                isLoading = true;
                                artistsPresenter.getTopChartArtists(currentPageTopChartArtists,Constants.TOP_ITEMS_LIMIT_CHART,Constants.API_KEY);
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
        artistsPresenter.getTopChartArtists(Constants.DEFAULT_PAGE_CHART,Constants.TOP_ITEMS_LIMIT_CHART,Constants.API_KEY);
    }

    @Override
    protected void search(String query) {
        isSearchingNow = true;
        artistsPresenter.searchArtist(Constants.DEFAULT_PAGE_SEARCH, Constants.TOP_ITEMS_LIMIT_SEARCH, query, Constants.API_KEY);
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
            artistsAdapter.setArtistList(artistList);
            isLoading = false;
        }
    }

    @Override
    public void searchArtists(List<Artist> artistList) {
        if (artistsAdapter != null){
            artistsAdapter.clearArtistList();
            artistsAdapter.setArtistList(artistList);
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

    @Override
    public boolean onQueryTextChange(String newText) {
        search(newText);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onClose() {
        artistsPresenter.getTopChartArtists(Constants.DEFAULT_PAGE_CHART,Constants.TOP_ITEMS_LIMIT_CHART,Constants.API_KEY);
        isSearchingNow = false;
        return false;
    }


    public interface OnFragmentInteractionListener{

    }
}
