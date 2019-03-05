package com.titanium.moodmusic.ui.fragments.artists;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.titanium.moodmusic.data.model.artists.Artist;
import com.titanium.moodmusic.ui.adapters.ArtistsAdapter;
import com.titanium.moodmusic.ui.adapters.MainPagerAdapter;
import com.titanium.moodmusic.ui.fragments.BaseFragment;
import com.titanium.moodmusic.di.modules.activity_level.ArtistsAdapterModule;
import com.titanium.moodmusic.di.modules.activity_level.ArtistsPresenterModule;
import com.titanium.moodmusic.di.components.*;
import com.titanium.moodmusic.ui.fragments.tracks.TracksFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ArtistsFragment extends BaseFragment
        implements IArtistsView
        , SearchView.OnQueryTextListener
        , SearchView.OnCloseListener {

    @BindView(R.id.recycler_artists)
    RecyclerView artistsRecyclerView;
    SearchView searchView;
    @BindView(R.id.progress_artist)
    ProgressBar progressBarMain;

    private onFragmentInteractionListener interactionListener;
    private Unbinder unbinder;

    @Inject
    IArtistsPresenter artistsPresenter;
    @Inject
    ArtistsAdapter artistsAdapter;

    private boolean isLoading, isSearchingNow;
    private int currentPageTopChartArtists = Constants.DEFAULT_PAGE_CHART;
    private int currentPageSearch = Constants.DEFAULT_PAGE_CHART;

    public ArtistsFragment() {
    }

    public static ArtistsFragment newInstance() {
        return new ArtistsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            interactionListener = (onFragmentInteractionListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        //инициализируем зависимости
        DaggerArtistsComponent.builder()
                .artistsAdapterModule(new ArtistsAdapterModule(getContext()))
                .artistsPresenterModule(new ArtistsPresenterModule(this))
                .build()
                .inject(this);

        artistsAdapter.setArtistItemClickListener(new ArtistsAdapter.ItemArtistClickListener() {
            @Override
            public void onItemClick(Artist artist) {
                Bundle bundle = new Bundle();
                bundle.putString(TracksFragment.EXTRA_ARIST_NAME, artist.getName());
                bundle.putBoolean(TracksFragment.EXTRA_CAN_SHOW_TRACKS_BY_ARTIST, true);
                interactionListener.onFragmentArtistInteraction(bundle);

                ViewPager viewPager = getActivity().findViewById(R.id.vp_main);
                if (viewPager != null)
                    viewPager.setCurrentItem(MainPagerAdapter.TRACKS_IDX);
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem searchItem = menu.findItem(R.id.search_field);
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(this);
            searchView.setOnCloseListener(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artists_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        artistsRecyclerView.setLayoutManager(linearLayoutManager);
        artistsRecyclerView.setAdapter(artistsAdapter);

        artistsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    if (!isSearchingNow) {
                        int totalItemCount = linearLayoutManager.getItemCount();
                        int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                        if (!isLoading) {
                            if ((totalItemCount - 1) == lastVisibleItem) {
                                currentPageTopChartArtists++;
                                isLoading = true;
                                artistsPresenter.getTopChartArtists(currentPageTopChartArtists, Constants.TOP_ITEMS_LIMIT_CHART, Constants.API_KEY);
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
        artistsPresenter.getTopChartArtists(Constants.DEFAULT_PAGE_CHART, Constants.TOP_ITEMS_LIMIT_CHART, Constants.API_KEY);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        interactionListener = null;
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
        if (artistsAdapter != null) {
            artistsAdapter.setArtistList(artistList);
            isLoading = false;
        }
    }

    @Override
    public void searchArtists(List<Artist> artistList) {
        if (artistsAdapter != null) {
            artistsAdapter.clearArtistList();
            artistsAdapter.setArtistList(artistList);
        }
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), getString(R.string.error_load), Toast.LENGTH_LONG).show();
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
        artistsAdapter.clearArtistList();
        artistsPresenter.getTopChartArtists(Constants.DEFAULT_PAGE_CHART, Constants.TOP_ITEMS_LIMIT_CHART, Constants.API_KEY);
        isSearchingNow = false;
        return false;
    }

    //интерфейс для взаимодействия между фрагментами
    public interface onFragmentInteractionListener {
        void onFragmentArtistInteraction(Bundle data);
    }
}
