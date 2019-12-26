package com.titanium.moodmusic.feature.artists.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.app.App;
import com.titanium.moodmusic.component.ui.BaseFragment;
import com.titanium.moodmusic.feature.artists.domain.entities.Artist;
import com.titanium.moodmusic.feature.artists.presentation.ArtistsPresenter;
import com.titanium.moodmusic.feature.artists.presentation.ArtistsView;
import com.titanium.moodmusic.shared.error.Message;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class ArtistsFragment extends BaseFragment
        implements ArtistsView
        , SwipeRefreshLayout.OnRefreshListener
        , SearchView.OnQueryTextListener
        , SearchView.OnCloseListener {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_artists)
    RecyclerView artistsRecyclerView;

    @BindView(R.id.progress_artist)
    ProgressBar progressBarMain;

    private SearchView searchView;

    private Unbinder unbinder;

    @Inject
    @InjectPresenter
    public ArtistsPresenter artistsPresenter;

    @ProvidePresenter
    ArtistsPresenter provideArtistsPresenter() {
        return artistsPresenter;
    }

    private ArtistsAdapter artistsAdapter;

    public static ArtistsFragment newInstance() {
        return new ArtistsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.appComponent.artistsFragmentSubComponentBuilder().build().inject(this);

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected int setupLayoutRes() {
        return R.layout.fragment_artists;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);

        initAdapter();
        initHandlers();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem menuItem = menu.findItem(R.id.search_field);
        this.searchView = (SearchView) menuItem.getActionView();
        this.searchView.setOnQueryTextListener(this);
        this.searchView.setOnCloseListener(this);
    }

    private void initAdapter() {
        artistsAdapter = new ArtistsAdapter(() -> artistsPresenter.onLoadTopChartArtists());
        artistsRecyclerView.setAdapter(artistsAdapter);
    }

    private void initHandlers() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgress() {
        progressBarMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBarMain.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadingItem() {
        artistsAdapter.setLoadingItem();
    }

    @Override
    public void hideLoadingItem() {
        artistsAdapter.removeLoadingItem();
    }

    @Override
    public void showChartArtists(List<Artist> artists) {
        artistsAdapter.setArtists(artists);
    }

    @Override
    public void showSearchedArtists(List<Artist> artists) {
        artistsAdapter.setArtists(artists);
    }

    @Override
    public void clearArtists() {
        artistsAdapter.clearArtistList();
    }

    @Override
    public void showError(Message message) {
        showErrorDialog(message.getErrorText());
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

    private void search(String query) {
        artistsPresenter.onSearchArtists(query);
    }

    @Override
    public boolean onClose() {
        artistsPresenter.onRefreshArtists();
        return false;
    }

    @Override
    public void onRefresh() {
        artistsPresenter.onRefreshArtists();
    }
}
