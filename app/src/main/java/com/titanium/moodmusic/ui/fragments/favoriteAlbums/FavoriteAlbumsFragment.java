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
import android.util.Log;
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
import com.titanium.moodmusic.data.db.AsyncDataLoader;
import com.titanium.moodmusic.data.model.artists.Artist;
import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;
import com.titanium.moodmusic.data.model.tracks.Track;
import com.titanium.moodmusic.ui.MusicAppication;
import com.titanium.moodmusic.ui.adapters.FavoriteAlbumsAdapter;
import com.titanium.moodmusic.ui.fragments.BaseFragment;
import com.titanium.moodmusic.ui.fragments.artists.ArtistsFragment;
import com.titanium.moodmusic.ui.fragments.favoriteAlbumTracks.FavoriteAlbumTracksFragment;
import com.titanium.moodmusic.ui.fragments.trackDetailWeb.WebFragment;
import com.titanium.moodmusic.ui.fragments.tracks.TracksFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavoriteAlbumsFragment extends BaseFragment
            implements IFavoriteAlbumsView{

    private RecyclerView tracksRecyclerView;
    private ProgressBar progressBarMain;
    private View emptyLayout;
    private Button buttonAddAlbum;

    private IFavoriteAlbumsPresenter favoriteAlbumsPresenter;
    private FavoriteAlbumsAdapter favoriteAlbumsAdapter;

    public FavoriteAlbumsFragment(){}

    public static FavoriteAlbumsFragment newInstance(){
        return new FavoriteAlbumsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AsyncDataLoader asyncDataLoader = new AsyncDataLoader();
        asyncDataLoader.setMusicDao(MusicAppication.getInstance().getMusicDatabase().musicDao());
        favoriteAlbumsPresenter = new FavoriteAlbumsPresenter(this,
                new FavoriteAlbumsInteractor(MusicAppication.getInstance().getMusicDatabase().musicDao()), asyncDataLoader);
        favoriteAlbumsAdapter = new FavoriteAlbumsAdapter(getContext());

        favoriteAlbumsAdapter.setAlbumBtnClickListener(new FavoriteAlbumsAdapter.ItemAlbumBtnClickListener() {
            @Override
            public void onItemBtnClick(FavoriteAlbum favoriteAlbum, int position) {
                prepareAlertDialogEditOrDeleteAlbum(favoriteAlbum,position);
            }
        });

        favoriteAlbumsAdapter.setItemAlbumClickListener(new FavoriteAlbumsAdapter.ItemClickListener() {
            @Override
            public void onItemAlbumClick(FavoriteAlbum favoriteAlbum) {

                for (Track track : favoriteAlbum.getTrackList()){
                    Log.d("TAG", "Перебираем треки в альюбоме - "+ track.getName());
                }

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
        tracksRecyclerView = view.findViewById(R.id.recycler_favorite_albums);
        progressBarMain = view.findViewById(R.id.progress_favorite_albums);
        emptyLayout = view.findViewById(R.id.empty_layout_favorite);
        buttonAddAlbum = view.findViewById(R.id.btn_add_album);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        tracksRecyclerView.setLayoutManager(linearLayoutManager);
        tracksRecyclerView.setAdapter(favoriteAlbumsAdapter);


        buttonAddAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareAlertDialogAddEditAlbum(null,false);
            }
        });

        return view;
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
    protected void search(String query) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadAlbums(List<FavoriteAlbum> albumList) {
        favoriteAlbumsAdapter.setFavoriteAlbumList(albumList);
    }

    @Override
    public void searchAlbums(List<FavoriteAlbum> albumList) {

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

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void hideEmpty() {

    }

    public List<FavoriteAlbum> getAllAlbums(){
        return favoriteAlbumsAdapter.getAllAlbums();
    }

    public void addTrackToAlbum(Track track, int positionAlbum){
        favoriteAlbumsAdapter.addTrackToAlbum(track,positionAlbum);
    }

    public void deleteTrackFromAlbum(Track track, int positionTrackInAlbum){
        Log.d("TAG","delete track");
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
