package com.titanium.moodmusic.feature.albums.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.component.common.DiffUtilCallBack;
import com.titanium.moodmusic.component.common.DiffUtilComparator;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsHolder> {

    private final List<Album> albums = new ArrayList<>();
    private final ItemAlbumBtnClickListener albumClickListener;
    private final ItemClickListener itemAlbumClickListener;

    private static final boolean DETECT_MOVES = false;
    private final DiffUtilCallBack<Album> diff = new DiffUtilCallBack<>(
            (DiffUtilComparator<Album, Album>) (firstItem, secondItem) ->
                    firstItem.getAlbumId() == secondItem.getAlbumId()
    );

    AlbumsAdapter(ItemAlbumBtnClickListener albumClickListener, ItemClickListener itemAlbumClickListener) {
        this.albumClickListener = albumClickListener;
        this.itemAlbumClickListener = itemAlbumClickListener;
    }

    @NonNull
    @Override
    public AlbumsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_album, viewGroup, false);

        return new AlbumsAdapter.AlbumsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AlbumsHolder albumsHolder, int i) {
        final Album itemAlbum = albums.get(i);
        albumsHolder.bind(itemAlbum);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    private Album getAlbumByPosition(int position) {
        return albums.get(position);
    }

    void setAlbums(List<Album> albums) {
        this.albums.addAll(albums);
        notifyDataSetChanged();
        //diff.getDiffResult(albums, DETECT_MOVES).dispatchUpdatesTo(this);
    }

    void addAlbum(Album album) {
        this.albums.add(album);
        notifyItemInserted(getItemCount() + 1);
    }

    void editAlbum(Album album) {
        int pos = albums.lastIndexOf(album);
        notifyItemChanged(pos);
    }

    void deleteAlbum(int position) {
        this.albums.remove(position);
        notifyItemRemoved(position);
    }

    void clearAlbums() {
        this.albums.clear();
    }

    class AlbumsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.num_album)
        TextView numAlbum;

        @BindView(R.id.name_album)
        TextView nameAlbum;

        @BindView(R.id.count_tracks)
        TextView countTracks;

        @BindView(R.id.album_menu)
        ImageView btnAlbumSettings;

        AlbumsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Album album) {
            this.numAlbum.setText(album.getAlbumId() + "."); //TODO добавить форматтер вместо хардкода
            this.nameAlbum.setText(album.getNameAlbum());
            this.countTracks.setText("треков:" + album.getTracks().size()); //TODO добавить форматтер вместо хардкода
        }

        @OnClick(R.id.cv_favorite_albums)
        void onItemAlbumClick() {
            itemAlbumClickListener.onItemAlbumClick(getAlbumByPosition(getAdapterPosition()));
        }

        @OnClick(R.id.album_menu)
        void onItemBtnClick() {
            albumClickListener.onItemBtnClick(getAlbumByPosition(getAdapterPosition()), getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemAlbumClick(Album album);
    }

    public interface ItemAlbumBtnClickListener {
        void onItemBtnClick(Album album, int position);
    }
}
