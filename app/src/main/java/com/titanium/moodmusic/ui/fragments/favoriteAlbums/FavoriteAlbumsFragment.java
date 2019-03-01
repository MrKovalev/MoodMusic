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

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;
import com.titanium.moodmusic.ui.adapters.FavoriteAlbumsAdapter;
import com.titanium.moodmusic.ui.fragments.BaseFragment;
import com.titanium.moodmusic.ui.fragments.artists.ArtistsFragment;
import com.titanium.moodmusic.ui.fragments.favoriteAlbumTracks.FavoriteAlbumTracksFragment;
import com.titanium.moodmusic.ui.fragments.trackDetailWeb.WebFragment;
import com.titanium.moodmusic.ui.fragments.tracks.TracksFragment;

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
        favoriteAlbumsPresenter = new FavoriteAlbumsPresenter(this, new FavoriteAlbumsInteractor());
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
                Log.d("TAG","CHANGE FRAGMENT");
                Fragment fragmentTracksAlbum = FavoriteAlbumTracksFragment.newInstance();

                Log.d("TAG","Fragment = "+ fragmentTracksAlbum);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Log.d("TAG","transaction = "+ transaction);

                transaction.addToBackStack(null);
                transaction.replace(R.id.container_album, new FavoriteAlbumTracksFragment());
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

    private void prepareAlertDialogAddEditAlbum(final FavoriteAlbum favoriteAlbum, final boolean isEdit){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.enter_name_add_album));

        final EditText input = new EditText(getContext());
        builder.setView(input);

        if (isEdit){
            if (favoriteAlbum != null)
                input.setText(favoriteAlbum.getNameAlbum());
        }

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!isEdit) {
                    favoriteAlbumsPresenter.addFavoriteAlbum(
                            new FavoriteAlbum(favoriteAlbumsAdapter.getLastAlbumId()
                                    ,input.getText().toString(),"0 треков"));
                } else {
                    favoriteAlbum.setNameAlbum(input.getText().toString());
                    favoriteAlbumsPresenter.editFavoriteAlbum(favoriteAlbum);
                }
            }
        });

        builder.setNegativeButton("Cancel", null);
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

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
