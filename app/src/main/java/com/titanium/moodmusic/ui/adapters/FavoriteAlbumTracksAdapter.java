package com.titanium.moodmusic.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.data.model.artists.Artist;
import com.titanium.moodmusic.data.model.tracks.Track;

import java.util.ArrayList;
import java.util.List;

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

        //Log.d("TAG","CAST TO - "+itemTrack.getArtist());

        /*if (itemTrack.getArtist() instanceof String){
            String artistName = (String) itemTrack.getArtist();
            albumDetailHolder.nameArtist.setText(artistName);
        } else {
            Artist artist = (Artist) itemTrack.getArtist();
            albumDetailHolder.nameArtist.setText(artist.getName());
        } */

        String artistName = (String) itemTrack.getArtist();
        albumDetailHolder.nameArtist.setText(artistName);

        albumDetailHolder.btnDeleteTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDeleteBtnClickListener.onItemBtnClick(itemTrack, albumDetailHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public void setTrackList(List<Track> trackList){
        for(Track track : trackList){
                this.trackList.add(track);
        }
        notifyDataSetChanged();
    }

    public void deleteTrackFromAlbum(Track track){
        this.trackList.remove(track);
        notifyDataSetChanged();
    }

    public void setTrack(Track track){
        this.trackList.add(track);
        notifyItemChanged(getItemCount() - 1);
    }

    public void setItemDeleteBtnClickListener(ItemDeleteBtnClickListener itemDeleteBtnClickListener) {
        this.itemDeleteBtnClickListener = itemDeleteBtnClickListener;
    }

    static class AlbumDetailHolder extends RecyclerView.ViewHolder {
        private ImageView imgTrack;
        private TextView nameTrack;
        private TextView nameArtist;
        private ImageButton btnDeleteTrack;

        AlbumDetailHolder(@NonNull View itemView) {
            super(itemView);
            imgTrack = itemView.findViewById(R.id.img_track);
            nameTrack = itemView.findViewById(R.id.name_track);
            nameArtist = itemView.findViewById(R.id.name_artist);
            btnDeleteTrack = itemView.findViewById(R.id.btn_add_track);
            btnDeleteTrack.setImageResource(R.drawable.ic_more_vert);
        }
    }

    public interface ItemDeleteBtnClickListener {
        void onItemBtnClick(Track track, int position);
    }

}
