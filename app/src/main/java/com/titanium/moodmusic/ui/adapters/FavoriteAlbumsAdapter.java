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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/** Класс отвечает за заполнение, отображение, взаимодействие,
 *  преобразование данных в RecyclerView избранных альбомов **/

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

    public FavoriteAlbum getAlbumByPosition(int position) {
        return favoriteAlbumList.get(position);
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
        notifyItemInserted(getItemCount() + 1);
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

    class AlbumsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.num_album)
        TextView numAlbum;
        @BindView(R.id.name_album)
        TextView nameAlbum;
        @BindView(R.id.count_tracks)
        TextView countTracks;
        @BindView(R.id.album_menu)
        ImageButton btnAlbumSettings;

        AlbumsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnAlbumSettings.setImageResource(R.drawable.ic_more_vert);
        }

        @OnClick(R.id.cv_favorite_albums)
        void onItemAlbumClick(){
            itemAlbumClickListener.onItemAlbumClick(getAlbumByPosition(getAdapterPosition()));
        }

        @OnClick(R.id.album_menu)
        void onItemBtnClick(){
            albumClickListener.onItemBtnClick(getAlbumByPosition(getAdapterPosition()), getAdapterPosition());
        }

    }

    public interface ItemClickListener {
        void onItemAlbumClick(FavoriteAlbum favoriteAlbum);
    }

    public interface ItemAlbumBtnClickListener {
        void onItemBtnClick(FavoriteAlbum favoriteAlbum, int position);
    }
}
