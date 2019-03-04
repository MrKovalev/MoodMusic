package com.titanium.moodmusic.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;
import com.titanium.moodmusic.data.model.tracks.Track;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAlbumsAdapter extends RecyclerView.Adapter<FavoriteAlbumsAdapter.AlbumsHolder> {

    private List<FavoriteAlbum> favoriteAlbumList = new ArrayList<>();
    private Context context;
    private ItemAlbumBtnClickListener albumClickListener;
    private ItemClickListener itemAlbumClickListener;

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
    public void onBindViewHolder(@NonNull final AlbumsHolder albumsHolder, int i) {
        final FavoriteAlbum itemAlbum = favoriteAlbumList.get(i);
        albumsHolder.numAlbum.setText(String.valueOf(itemAlbum.getAlbumId() + "."));
        albumsHolder.nameAlbum.setText(itemAlbum.getNameAlbum());
        albumsHolder.countTracks.setText("треков:" + itemAlbum.getTrackList().size());

        albumsHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAlbumClickListener.onItemAlbumClick(itemAlbum);
            }
        });

        albumsHolder.btnAlbumSettings.setImageResource(R.drawable.ic_more_vert);
        albumsHolder.btnAlbumSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                albumClickListener.onItemBtnClick(itemAlbum, albumsHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (favoriteAlbumList != null) {
            return favoriteAlbumList.size();
        }

        return 0;
    }

    public List<FavoriteAlbum> getAllAlbums() {
        return this.favoriteAlbumList;
    }

    public void addTrackToAlbum(Track newTrack, int positionAlbum) {
        FavoriteAlbum whichAlbumAdd = favoriteAlbumList.get(positionAlbum);
        whichAlbumAdd.addNewTrack(newTrack);
        notifyDataSetChanged();
    }

    public void deleteTrackFromAlbum(Track oldTrack, int positionTrackInAlbum) {
        int indexAlbum = 0;
        boolean isFounded = false;

        for (int k = 0; k < favoriteAlbumList.size(); k++) {
            List<Track> trackList = favoriteAlbumList.get(k).getTrackList();
            for (int i = 0; i < trackList.size(); i++) {
                if (trackList.get(i).getName().equalsIgnoreCase(oldTrack.getName())) {
                    isFounded = true;
                    break;
                }
            }

            if (isFounded) {
                indexAlbum = k;
                break;
            }
        }

        FavoriteAlbum whichAlbumDelete = favoriteAlbumList.get(indexAlbum);
        whichAlbumDelete.deleteOldTrack(oldTrack, positionTrackInAlbum);
        notifyDataSetChanged();
    }

    public void setFavoriteAlbumList(List<FavoriteAlbum> favoriteAlbums) {
        this.favoriteAlbumList.addAll(favoriteAlbums);
        notifyDataSetChanged();
    }

    public void addFavoriteAlbum(FavoriteAlbum favoriteAlbum) {
        this.favoriteAlbumList.add(favoriteAlbum);
        notifyItemChanged(getItemCount() - 1);
    }


    public void editFavoriteAlbum(FavoriteAlbum favoriteAlbum) {
        int pos = favoriteAlbumList.lastIndexOf(favoriteAlbum);
        notifyItemChanged(pos);
    }

    public void deleteFavoriteAlbum(int position) {
        this.favoriteAlbumList.remove(position);
        notifyItemRemoved(position);
    }

    public int getLastAlbumId() {
        return this.favoriteAlbumList.size() + 1;
    }

    public void setAlbumBtnClickListener(ItemAlbumBtnClickListener albumClickListener) {
        this.albumClickListener = albumClickListener;
    }

    public void setItemAlbumClickListener(ItemClickListener itemAlbumClickListener) {
        this.itemAlbumClickListener = itemAlbumClickListener;
    }

    public interface ItemClickListener {
        void onItemAlbumClick(FavoriteAlbum favoriteAlbum);
    }

    public interface ItemAlbumBtnClickListener {
        void onItemBtnClick(FavoriteAlbum favoriteAlbum, int position);
    }

    static class AlbumsHolder extends RecyclerView.ViewHolder {

        private TextView numAlbum;
        private TextView nameAlbum;
        private TextView countTracks;
        private ImageButton btnAlbumSettings;

        AlbumsHolder(@NonNull View itemView) {
            super(itemView);
            numAlbum = itemView.findViewById(R.id.num_album);
            nameAlbum = itemView.findViewById(R.id.name_album);
            countTracks = itemView.findViewById(R.id.count_tracks);
            btnAlbumSettings = itemView.findViewById(R.id.album_menu);
        }
    }
}
