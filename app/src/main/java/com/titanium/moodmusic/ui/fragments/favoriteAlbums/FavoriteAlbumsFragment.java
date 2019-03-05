package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.titanium.moodmusic.R;
import com.titanium.moodmusic.data.model.artists.Artist;
import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;
import com.titanium.moodmusic.data.model.tracks.Track;
import com.titanium.moodmusic.ui.adapters.FavoriteAlbumsAdapter;
import com.titanium.moodmusic.di.components.*;
import com.titanium.moodmusic.di.modules.activity_level.FavoriteAlbumsAdapterModule;
import com.titanium.moodmusic.di.modules.activity_level.FavoriteAlbumsPresenterModule;
import com.titanium.moodmusic.di.modules.app_level.AppModule;
import com.titanium.moodmusic.di.modules.app_level.RoomModule;
import com.titanium.moodmusic.ui.fragments.BaseFragment;
import com.titanium.moodmusic.ui.fragments.favoriteAlbumTracks.FavoriteAlbumTracksFragment;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FavoriteAlbumsFragment extends BaseFragment
            implements IFavoriteAlbumsView{

    @BindView(R.id.recycler_favorite_albums)
    RecyclerView tracksRecyclerView;
    @BindView(R.id.progress_favorite_albums)
    ProgressBar progressBarMain;
    @BindView(R.id.btn_add_album)
    Button buttonAddAlbum;

    private Unbinder unbinder;

    @Inject
    IFavoriteAlbumsPresenter favoriteAlbumsPresenter;

    @Inject
    FavoriteAlbumsAdapter favoriteAlbumsAdapter;

    public FavoriteAlbumsFragment(){}

    public static FavoriteAlbumsFragment newInstance(){
        return new FavoriteAlbumsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerFavoriteAlbumsComponent.builder()
                .favoriteAlbumsAdapterModule(new FavoriteAlbumsAdapterModule(getContext()))
                .appModule(new AppModule(getActivity().getApplication()))
                .favoriteAlbumsPresenterModule(new FavoriteAlbumsPresenterModule(this))
                .roomModule(new RoomModule(getActivity().getApplication()))
                .build()
                .inject(this);

        favoriteAlbumsAdapter.setAlbumBtnClickListener(new FavoriteAlbumsAdapter.ItemAlbumBtnClickListener() {
            @Override
            public void onItemBtnClick(FavoriteAlbum favoriteAlbum, int position) {
                prepareAlertDialogEditOrDeleteAlbum(favoriteAlbum,position);
            }
        });

        favoriteAlbumsAdapter.setItemAlbumClickListener(new FavoriteAlbumsAdapter.ItemClickListener() {
            @Override
            public void onItemAlbumClick(FavoriteAlbum favoriteAlbum) {

                Fragment fragmentTracksAlbum = FavoriteAlbumTracksFragment
                        .newInstance(setTrackList(favoriteAlbum.getTrackList()), favoriteAlbum.getNameAlbum());

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.container_album, fragmentTracksAlbum);
                transaction.commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_albums_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        tracksRecyclerView.setLayoutManager(linearLayoutManager);
        tracksRecyclerView.setAdapter(favoriteAlbumsAdapter);

        return view;
    }

    @OnClick(R.id.btn_add_album)
    void onBtnAdded(){
        prepareAlertDialogAddEditAlbum(null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        favoriteAlbumsPresenter.getFavoriteAlbums();
    }

    @Override
    public void onStop() {
        super.onStop();
        favoriteAlbumsPresenter.saveAlbumsToDatabase(favoriteAlbumsAdapter.getAllAlbums());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void search(String query) { }

    @Override
    public void loadAlbums(List<FavoriteAlbum> albumList) {
        favoriteAlbumsAdapter.setFavoriteAlbumList(albumList);
    }

    @Override
    public void addAlbum(FavoriteAlbum favoriteAlbum) {
        favoriteAlbumsAdapter.addFavoriteAlbum(favoriteAlbum);
    }

    @Override
    public void editAlbum(FavoriteAlbum favoriteAlbum) {
        favoriteAlbumsAdapter.editFavoriteAlbum(favoriteAlbum);
    }

    @Override
    public void deleteAlbum(int position) {
        favoriteAlbumsAdapter.deleteFavoriteAlbum(position);
    }

    public List<FavoriteAlbum> getAllAlbums(){
        return favoriteAlbumsAdapter.getAllAlbums();
    }

    public void addTrackToAlbum(Track track, int positionAlbum){
        favoriteAlbumsAdapter.addTrackToAlbum(track,positionAlbum);
    }

    public void deleteTrackFromAlbum(Track track, int positionTrackInAlbum){
        favoriteAlbumsAdapter.deleteTrackFromAlbum(track, positionTrackInAlbum);
    }

    private void prepareAlertDialogAddEditAlbum(final FavoriteAlbum favoriteAlbum, final boolean isEdit){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.enter_name_add_album));

        final EditText input = new EditText(getContext());
        builder.setView(input);

        if (isEdit){
            if (favoriteAlbum != null)
                input.setText(favoriteAlbum.getNameAlbum());
        }

        builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!isEdit) {
                    favoriteAlbumsPresenter.addFavoriteAlbum(
                            new FavoriteAlbum(favoriteAlbumsAdapter.getLastAlbumId()
                                    ,input.getText().toString(),"треков:0"));
                } else {
                    favoriteAlbum.setNameAlbum(input.getText().toString());
                    favoriteAlbumsPresenter.editFavoriteAlbum(favoriteAlbum);
                }
            }
        });

        builder.setNegativeButton("Отменить", null);
        builder.show();
    }

    private void prepareAlertDialogEditOrDeleteAlbum(final FavoriteAlbum favoriteAlbum, final int position){
        String[] choices = {"Переименовать", "Удалить"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.choose_album_act));

        builder.setItems(choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        dialog.dismiss();
                        prepareAlertDialogAddEditAlbum(favoriteAlbum, true);
                        break;
                    case 1:
                        favoriteAlbumsPresenter.deleteFavoriteAlbum(position);
                        break;
                }
            }
        });

        builder.setNegativeButton("Отменить", null);
        builder.show();
    }

    private String setTrackList(List<Track> tracks){
        //проставляем всем трекам артиста в виде строки, сделано из-за проблем
        // при разном ответе с сервера и последующим toJson
        for (Track track : tracks){
            if (track.getArtist() instanceof String){
                String artistName = (String) track.getArtist();
                track.setArtist(artistName);
            } else {
                Artist artist = (Artist) track.getArtist();
                track.setArtist(artist.getName());
            }
        }

        Gson gson = new GsonBuilder().create();
        Type trackType = new TypeToken<List<Track>>(){}.getType();
        return gson.toJson(tracks);
    }
}
