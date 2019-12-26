package com.titanium.moodmusic.feature.album.detail.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.app.App;
import com.titanium.moodmusic.component.ui.BaseFragment;
import com.titanium.moodmusic.feature.album.detail.di.AlbumDetailsModule;
import com.titanium.moodmusic.feature.album.detail.presentation.AlbumDetailsPresenter;
import com.titanium.moodmusic.feature.album.detail.presentation.AlbumDetailsView;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
import com.titanium.moodmusic.shared.tracks.Track;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class AlbumDetailsFragment extends BaseFragment implements AlbumDetailsView {

    public static final String EXTRA_ALBUM_KEY = "EXTRA_ALBUM_KEY";

    @BindView(R.id.recycler_tracks_album)
    RecyclerView recyclerViewTracks;

    @BindView(R.id.name_current_album)
    TextView nameAlbum;

    @BindView(R.id.back_go)
    ImageView backBtn;

    @Inject
    @InjectPresenter
    AlbumDetailsPresenter albumDetailsPresenter;

    @ProvidePresenter
    AlbumDetailsPresenter providesAlbumDetailsPresenter() {
        return albumDetailsPresenter;
    }

    private Unbinder unbinder;
    private AlbumDetailsAdapter albumDetailsAdapter = new AlbumDetailsAdapter(
            (track) -> {}
    );

    public static AlbumDetailsFragment newInstance(Album album) {
        AlbumDetailsFragment albumDetailsFragment = new AlbumDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_ALBUM_KEY, album);

        albumDetailsFragment.setArguments(bundle);
        return albumDetailsFragment;
    }

    @Override
    protected int setupLayoutRes() {
        return R.layout.fragment_album_tracks;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.appComponent.albumDetailsFragmentSubComponentBuilder()
                .albumDetailsModule(new AlbumDetailsModule(this))
                .build()
                .inject(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        initAdapter();
    }

    private void initAdapter() {
        recyclerViewTracks.setAdapter(albumDetailsAdapter);
    }

    @OnClick(R.id.back_go)
    void onBackGo() {
        FragmentManager fragmentManager = getFragmentManager();

        if (fragmentManager != null) {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void showTracks(List<Track> tracks) {
        albumDetailsAdapter.setTracks(tracks);
    }

    @Override
    public void setAlbumTitle(String title) {
        nameAlbum.setText(title);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}