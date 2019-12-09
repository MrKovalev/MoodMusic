package com.titanium.moodmusic.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.data.model.tracks.Track;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavoriteAlbumTracksAdapter extends RecyclerView.Adapter<FavoriteAlbumTracksAdapter.AlbumDetailHolder> {

    private ItemDeleteBtnClickListener itemDeleteBtnClickListener;
    private List<Track> trackList = new ArrayList<>();

    @NonNull
    @Override
    public AlbumDetailHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.track_item, viewGroup, false);

        return new FavoriteAlbumTracksAdapter.AlbumDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AlbumDetailHolder albumDetailHolder, int i) {
        final Track itemTrack = trackList.get(i);
        albumDetailHolder.imgTrack.setImageResource(R.drawable.ic_audiotrack_orange);
        albumDetailHolder.nameTrack.setText(itemTrack.getName());

        String artistName = (String) itemTrack.getArtist();
        albumDetailHolder.nameArtist.setText(artistName);
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public void setTrackList(List<Track> trackList){
        this.trackList.addAll(trackList);
        notifyDataSetChanged();
    }

    public void deleteTrackFromAlbum(Track track){
        this.trackList.remove(track);
        notifyDataSetChanged();
    }

    public void setTrack(Track track){
        this.trackList.add(track);
        notifyItemInserted(getItemCount() + 1);
    }

    public Track getTrackByPosition(int position) {
        return trackList.get(position);
    }

    public void setItemDeleteBtnClickListener(ItemDeleteBtnClickListener itemDeleteBtnClickListener) {
        this.itemDeleteBtnClickListener = itemDeleteBtnClickListener;
    }

    class AlbumDetailHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_track)
        ImageView imgTrack;
        @BindView(R.id.name_track)
        TextView nameTrack;
        @BindView(R.id.name_artist)
        TextView nameArtist;
        @BindView(R.id.btn_add_track)
        ImageButton btnDeleteTrack;

        AlbumDetailHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnDeleteTrack.setImageResource(R.drawable.ic_more_vert);
        }

        @OnClick(R.id.btn_add_track)
        void onItemBtnClick(){
            itemDeleteBtnClickListener.onItemBtnClick(getTrackByPosition(getAdapterPosition()), getAdapterPosition());
        }
    }

    public interface ItemDeleteBtnClickListener {
        void onItemBtnClick(Track track, int position);
    }
}
