package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;
import com.titanium.moodmusic.ui.adapters.FavoriteAlbumsAdapter;
import com.titanium.moodmusic.ui.fragments.BaseFragment;

import java.util.List;

public class FavoriteAlbumsFragment extends BaseFragment
            implements IFavoriteAlbumsView{

    private RecyclerView tracksRecyclerView;
    private ProgressBar progressBarMain;
    private View emptyLayout;

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

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_albums_fragment, container, false);
        tracksRecyclerView = view.findViewById(R.id.recycler_favorite_albums);
        progressBarMain = view.findViewById(R.id.progress_favorite_albums);
        emptyLayout = view.findViewById(R.id.empty_layout_favorite);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        tracksRecyclerView.setLayoutManager(linearLayoutManager);
        tracksRecyclerView.setAdapter(favoriteAlbumsAdapter);

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
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void hideEmpty() {

    }
}
