package com.titanium.moodmusic.feature.albums.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.app.App;
import com.titanium.moodmusic.component.ui.BaseFragment;
import com.titanium.moodmusic.feature.album.detail.ui.AlbumDetailsFragment;
import com.titanium.moodmusic.feature.albums.presentation.AlbumsPresenter;
import com.titanium.moodmusic.feature.albums.presentation.AlbumsView;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
import com.titanium.moodmusic.shared.error.Message;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class AlbumsFragment extends BaseFragment
        implements AlbumsView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_favorite_albums)
    RecyclerView tracksRecyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.progress_favorite_albums)
    ProgressBar progressBarMain;

    @BindView(R.id.btn_add_album)
    Button buttonAddAlbum;

    private Unbinder unbinder;

    @Inject
    @InjectPresenter
    public AlbumsPresenter albumsPresenter;

    @ProvidePresenter
    AlbumsPresenter providePresenter() {
        return albumsPresenter;
    }

    private AlbumsAdapter albumsAdapter = new AlbumsAdapter(
            (album, position) -> albumsPresenter.onSelectMenuAlbumClicked(album, position),
            (album) -> albumsPresenter.onOpenAlbumClicked(album)
    );

    public AlbumsFragment() {
    }

    public static AlbumsFragment newInstance() {
        return new AlbumsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.appComponent.albumsFragmentSubComponentBuilder().build().inject(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setupLayoutRes() {
        return R.layout.fragment_albums;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);

        initAdapter();
        initHandlers();
    }

    private void initAdapter() {
        tracksRecyclerView.setAdapter(albumsAdapter);
    }

    private void initHandlers() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @OnClick(R.id.btn_add_album)
    void onAddBtnClicked() {
        albumsPresenter.onAddNewAlbumClicked();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showAlbums(List<Album> albumList) {
        albumsAdapter.clearAlbums();
        albumsAdapter.setAlbums(albumList);
    }

    @Override
    public void showAddNewAlbumDialog() {
        prepareAddAlbumDialog();
    }

    private void prepareAddAlbumDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final EditText input = new EditText(getContext());

        builder.setTitle(getString(R.string.enter_name_add_album))
                .setView(input)
                .setPositiveButton(R.string.btn_save_text, (dialog, which) -> {
                    albumsPresenter.onAddAlbum(
                            new Album(
                                    0,
                                    input.getText().toString(),
                                    "треков:0", //TODO добавить форматтер вместо хардкода
                                    new ArrayList<>()
                            )
                    );
                })
                .setNegativeButton(R.string.btn_cancel_text, null)
                .show();
    }

    @Override
    public void showChooseActionDialog(Album album, int position) {
        prepareChooseDialog(album, position);
    }

    private void prepareChooseDialog(Album album, int position) {
        String[] choices = getResources().getStringArray(R.array.dialog_actions);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle(getString(R.string.choose_album_act))
                .setItems(choices, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            dialog.dismiss();
                            albumsPresenter.onEditAlbumSelected(album);
                            break;
                        case 1:
                            albumsPresenter.onDeleteAlbumSelected(position, album);
                            break;
                    }
                })
                .setNegativeButton(R.string.btn_cancel_text, null)
                .show();
    }

    @Override
    public void showRenameAlbumDialog(Album album) {
        prepareRenameAlbumDialog(album);
    }

    private void prepareRenameAlbumDialog(Album album) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final EditText input = new EditText(getContext());

        input.setText(album.getNameAlbum());

        builder.setTitle(R.string.change_name_album_text)
                .setView(input)
                .setPositiveButton(R.string.btn_save_text, (dialog, which) -> {
                    album.setNameAlbum(input.getText().toString());
                    albumsPresenter.onEditAlbum(album);
                })
                .setNegativeButton(R.string.btn_cancel_text, null)
                .show();
    }

    @Override
    public void addAlbum(Album album) {
        albumsAdapter.addAlbum(album);
    }

    @Override
    public void editAlbum(Album album) {
        albumsAdapter.editAlbum(album);
    }

    @Override
    public void deleteAlbum(int position) {
        albumsAdapter.deleteAlbum(position);
    }

    @Override
    public void onRefresh() {
        albumsPresenter.getFavoriteAlbums();
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
    public void openAlbumDetails(Album album) {
        Fragment albumDetailsFragment = AlbumDetailsFragment.newInstance(album);
        FragmentManager fragmentManager = getFragmentManager();

        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.main_container, albumDetailsFragment)
                    .commit();
        }
    }

    @Override
    public void showError(Message message) {
        showErrorDialog(message.getErrorText());
    }
}
