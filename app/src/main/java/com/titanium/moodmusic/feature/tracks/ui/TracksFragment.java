package com.titanium.moodmusic.feature.tracks.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.app.App;
import com.titanium.moodmusic.component.ui.BaseFragment;
import com.titanium.moodmusic.feature.track.detail.WebFragment;
import com.titanium.moodmusic.feature.tracks.presentation.TracksPresenter;
import com.titanium.moodmusic.feature.tracks.presentation.TracksView;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
import com.titanium.moodmusic.shared.error.Message;
import com.titanium.moodmusic.shared.tracks.Track;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class TracksFragment extends BaseFragment implements TracksView,
        SwipeRefreshLayout.OnRefreshListener,
        SearchView.OnQueryTextListener,
        SearchView.OnCloseListener {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_tracks)
    RecyclerView tracksRecyclerView;

    @BindView(R.id.progress_tracks)
    ProgressBar progressBarMain;

    private SearchView searchView;

    @Inject
    @InjectPresenter
    public TracksPresenter tracksPresenter;

    @ProvidePresenter
    TracksPresenter provideTrackPresenter() {
        return tracksPresenter;
    }

    private final TracksAdapter tracksAdapter = new TracksAdapter(
            track -> tracksPresenter.onOpenTrackDetailClicked(track),
            track -> tracksPresenter.onAddTrackClicked(track),
            () -> tracksPresenter.onLoadTopChartTracks());

    private Unbinder unbinder;

    public TracksFragment() {
    }

    public static TracksFragment newInstance() {
        return new TracksFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.appComponent.tracksFragmentSubComponentBuilder().build().inject(this);

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected int setupLayoutRes() {
        return R.layout.fragment_tracks;
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
        tracksRecyclerView.setAdapter(tracksAdapter);
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
        tracksAdapter.setLoadingItem();
    }

    @Override
    public void hideLoadingItem() {
        tracksAdapter.removeLoadingItem();
    }

    @Override
    public void showLoadedTracks(List<Track> trackModelList) {
        tracksAdapter.setTracks(trackModelList);
    }

    @Override
    public void showSearchedTracks(List<Track> trackModelList) {
        tracksAdapter.setTracks(trackModelList);
    }

    @Override
    public void showSearchedByArtistTracks(List<Track> trackModelList) {
        tracksAdapter.clearTracksList();
        tracksAdapter.setTracks(trackModelList);
    }

    @Override
    public void openTrackDetail(Track track) {
        Fragment fragmentWebView = WebFragment.newInstance(track.getUrl());

        if (getFragmentManager() != null) {
            getFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container_track, fragmentWebView)
                    .commit();
        }
    }

    @Override
    public void clearTracks() {
        tracksAdapter.clearTracksList();
    }

    @Override
    public void showError(Message message) {
        showErrorDialog(message.getErrorText());
    }

    @Override
    public boolean onQueryTextChange(String query) {
        search(query);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onClose() {
        tracksPresenter.onRefreshTracks();
        return false;
    }

    @Override
    public void onRefresh() {
        tracksPresenter.onRefreshTracks();
    }

    private void search(String query) {
        tracksPresenter.onSearchTrack(query);
    }

    @Override
    public void showAddTrackDialog(Track selectedTrack, List<Album> albums) {
        List<String> items = new ArrayList<>();

        for (Album album : albums) {
            items.add(album.getNameAlbum());
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setTitle(getString(R.string.choose_album_to_add_track))
                .setSingleChoiceItems(items.toArray(new String[0]), -1, (dialog, which) -> {
                    Album album = getSelectedAlbumByPosition(which, albums);
                    dialog.dismiss();
                    tracksPresenter.onSaveTrack(album, selectedTrack);
                })
                .setNegativeButton(getString(R.string.btn_cancel_text), null).show();
    }

    private Album getSelectedAlbumByPosition(int position, List<Album> albums) {
        return albums.get(position);
    }

    @Override
    public void showSuccessSavedTrack() {
        Toast.makeText(getContext(), this.getString(R.string.success_added_track_text), Toast.LENGTH_SHORT).show();
    }
}
