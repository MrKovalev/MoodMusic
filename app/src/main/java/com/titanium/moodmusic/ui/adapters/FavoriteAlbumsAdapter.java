package com.titanium.moodmusic.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAlbumsAdapter extends RecyclerView.Adapter<FavoriteAlbumsAdapter.AlbumsHolder> {

    private List<FavoriteAlbum> favoriteAlbumList = new ArrayList<>();
    private Context context;
    private View.OnClickListener trackListener;

    public FavoriteAlbumsAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public AlbumsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.favorite_album_item, viewGroup, false);

        return new FavoriteAlbumsAdapter.AlbumsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsHolder albumsHolder, int i) {
        FavoriteAlbum itemAlbum = favoriteAlbumList.get(i);
        albumsHolder.numAlbum.setText(String.valueOf(itemAlbum.getAlbumId()));
        albumsHolder.nameAlbum.setText(itemAlbum.getNameAlbum());
        albumsHolder.countTracks.setText(itemAlbum.getCountSongsInAlbum());
    }

    @Override
    public int getItemCount() {
        if (favoriteAlbumList != null){
            return favoriteAlbumList.size();
        }

        return 0;
    }

    public void setFavoriteAlbumList(List<FavoriteAlbum> favoriteAlbums){
        this.favoriteAlbumList.addAll(favoriteAlbums);
        notifyDataSetChanged();
    }

    static class AlbumsHolder extends RecyclerView.ViewHolder{

        private TextView numAlbum;
        private TextView nameAlbum;
        private TextView countTracks;

        AlbumsHolder(@NonNull View itemView) {
            super(itemView);
            numAlbum = itemView.findViewById(R.id.num_album);
            nameAlbum = itemView.findViewById(R.id.name_album);
            countTracks = itemView.findViewById(R.id.count_tracks);
        }
    }
}
