package com.titanium.moodmusic.ui.adapters;

import android.content.Context;
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
import com.titanium.moodmusic.data.model.tracks.Track;
import com.titanium.moodmusic.data.model.artists.Artist;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.TracksHolder> {

    private List<Track> trackList = new ArrayList<>();
    private Context context;
    private ItemTrackClickListener trackItemClickListener;
    private ItemTrackBtnAddClickListener trackBtnAddClickListener;

    public TracksAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TracksHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.track_item, viewGroup, false);

        return new TracksHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TracksHolder tracksHolder, int i) {
        final Track itemTrack = trackList.get(i);
        tracksHolder.imgTrack.setImageResource(R.drawable.ic_audiotrack_orange);
        tracksHolder.nameTrack.setText(itemTrack.getName());

        if (itemTrack.getArtist() instanceof String) {
            String artistName = (String) itemTrack.getArtist();
            tracksHolder.nameArtist.setText(artistName);
        } else {
            Artist artist = (Artist) itemTrack.getArtist();
            tracksHolder.nameArtist.setText(artist.getName());
        }
    }

    @Override
    public int getItemCount() {
        if (trackList != null) {
            return trackList.size();
        }

        return 0;
    }

    public void setTrackList(List<Track> trackList) {
        for (Track track : trackList) {
            if (checkUniqueTrack(track))
                this.trackList.add(track);
        }
        notifyDataSetChanged();
    }

    public List<Track> getTrackList() {
        return this.trackList;
    }

    public void setTrack(Track track) {
        this.trackList.add(track);
        notifyItemInserted(getItemCount() + 1);
    }

    public Track getTrackByPosition(int position) {
        return trackList.get(position);
    }

    public void clearTracktList() {
        if (trackList != null) {
            trackList.clear();
            notifyDataSetChanged();
        }
    }

    private boolean checkUniqueTrack(Track track) {
        for (Track trackForAnalyse : this.trackList) {
            if (trackForAnalyse.getName().equalsIgnoreCase(track.getName()))
                return false;
        }

        return true;
    }

    public void setTrackItemClickListener(ItemTrackClickListener itemClickListener) {
        this.trackItemClickListener = itemClickListener;
    }

    public void setTrackBtnAddClickListener(ItemTrackBtnAddClickListener trackBtnAddClickListener) {
        this.trackBtnAddClickListener = trackBtnAddClickListener;
    }

    class TracksHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_track)
        ImageView imgTrack;
        @BindView(R.id.name_track)
        TextView nameTrack;
        @BindView(R.id.name_artist)
        TextView nameArtist;
        @BindView(R.id.btn_add_track)
        ImageButton btnAddTrackToAlbum;

        TracksHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnAddTrackToAlbum.setImageResource(R.drawable.ic_playlist_add_orange);
        }

        @OnClick(R.id.cv_tracks)
        void onItemClicked() {
            if (trackItemClickListener != null)
                trackItemClickListener.onItemClick(getTrackList(), getTrackByPosition(getAdapterPosition()), getAdapterPosition());
        }

        @OnClick(R.id.btn_add_track)
        void onItemBtnClick() {
            trackBtnAddClickListener.onItemClick(getTrackByPosition(getAdapterPosition()));
        }
    }

    public interface ItemTrackClickListener {
        void onItemClick(List<Track> trackList, Track track, int position);
    }

    public interface ItemTrackBtnAddClickListener {
        void onItemClick(Track track);
    }
}
