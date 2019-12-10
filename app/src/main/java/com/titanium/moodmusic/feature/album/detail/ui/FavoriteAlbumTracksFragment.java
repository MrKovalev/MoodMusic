package com.titanium.moodmusic.feature.album.detail.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.titanium.moodmusic.R;
import com.titanium.moodmusic.feature.tracks.data.model.Track;
import com.titanium.moodmusic.component.ui.BaseFragment;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FavoriteAlbumTracksFragment extends BaseFragment {

    public static final String EXTRA_ALBUM_TRACK_LIST = "EXTRA_ALBUM_TRACK_LIST";
    public static final String EXTRA_NAME_ALBUM = "EXTRA_NAME_ALBUM";

    @BindView(R.id.recycler_tracks_album)
    RecyclerView recyclerViewTracks;
    @BindView(R.id.name_current_album)
    TextView nameAlbum;
    @BindView(R.id.back_go)
    ImageButton backBtn;

    private Unbinder unbinder;
    private FavoriteAlbumTracksAdapter favoriteAlbumTracksAdapter;
    private onFragmentTrackDeleteFromAlbumInteractionListener onFragmentTrackDeleteFromAlbumInteractionListener;


    public static FavoriteAlbumTracksFragment newInstance(String listTracks, String nameAlbum) {
        FavoriteAlbumTracksFragment favoriteAlbumTracksFragment = new FavoriteAlbumTracksFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NAME_ALBUM, nameAlbum);
        bundle.putString(EXTRA_ALBUM_TRACK_LIST, listTracks);

        favoriteAlbumTracksFragment.setArguments(bundle);
        return favoriteAlbumTracksFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            onFragmentTrackDeleteFromAlbumInteractionListener = (onFragmentTrackDeleteFromAlbumInteractionListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favoriteAlbumTracksAdapter = new FavoriteAlbumTracksAdapter();
        favoriteAlbumTracksAdapter.setItemDeleteBtnClickListener(new FavoriteAlbumTracksAdapter.ItemDeleteBtnClickListener() {
            @Override
            public void onItemBtnClick(Track track, int position) {
                prepareDeleteDialog(track, position);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_album_tracks_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);
        nameAlbum.setText(getArguments().getString(EXTRA_NAME_ALBUM));

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewTracks.setLayoutManager(linearLayoutManager);
        recyclerViewTracks.setAdapter(favoriteAlbumTracksAdapter);

        String listTracks = getArguments().getString(EXTRA_ALBUM_TRACK_LIST);
        List<Track> trackList = getTrackList(listTracks);
        favoriteAlbumTracksAdapter.setTrackList(trackList);

        return view;
    }

    @OnClick(R.id.back_go)
    void onBackGo(){
        FragmentManager fragmentManager = getFragmentManager();

        if (fragmentManager != null) {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onFragmentTrackDeleteFromAlbumInteractionListener = null;
    }

    @Override
    protected void search(String query) { }

    private List<Track> getTrackList(String tracks) {
        Gson gson = new GsonBuilder().create();
        Type trackType = new TypeToken<List<Track>>() {
        }.getType();

        return gson.fromJson(tracks, trackType);
    }

    private void prepareDeleteDialog(final Track track, final int positionAlbum) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.delete_track_from_album));
        builder.setMessage("Вы уверены что хотите удалить данный трек?");

        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onFragmentTrackDeleteFromAlbumInteractionListener
                        .onFragmentTrackDeleteFromAlbumInteraction(track, positionAlbum);

                favoriteAlbumTracksAdapter.deleteTrackFromAlbum(track);
                Toast.makeText(getContext(), "Трек успешно удалён", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Отменить", null);
        builder.show();
    }

    public void setOnFragmentTrackDeleteFromAlbumInteractionListener(FavoriteAlbumTracksFragment.onFragmentTrackDeleteFromAlbumInteractionListener onFragmentTrackDeleteFromAlbumInteractionListener) {
        this.onFragmentTrackDeleteFromAlbumInteractionListener = onFragmentTrackDeleteFromAlbumInteractionListener;
    }

    public interface onFragmentTrackDeleteFromAlbumInteractionListener {
        void onFragmentTrackDeleteFromAlbumInteraction(Track track, int positionTrackInAlbum);
    }
}
