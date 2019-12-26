package com.titanium.moodmusic.feature.album.detail.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.shared.tracks.Track;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumDetailsAdapter extends RecyclerView.Adapter<AlbumDetailsAdapter.AlbumDetailHolder> {

    private final ItemClickListener itemClickListener;
    private final List<Track> tracks = new ArrayList<>();

    AlbumDetailsAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public AlbumDetailHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_track, viewGroup, false);

        return new AlbumDetailsAdapter.AlbumDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AlbumDetailHolder albumDetailHolder, int i) {
        final Track itemTrack = tracks.get(i);
        albumDetailHolder.bind(itemTrack);
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public void setTracks(List<Track> tracks) {
        this.tracks.addAll(tracks);
        notifyDataSetChanged();
    }

    public void setTrack(Track track) {
        this.tracks.add(track);
        notifyItemInserted(getItemCount() + 1);
    }

    private Track getTrackByPosition(int position) {
        return tracks.get(position);
    }


    class AlbumDetailHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_track)
        ImageView imgTrack;

        @BindView(R.id.name_track)
        TextView nameTrack;

        @BindView(R.id.name_artist)
        TextView nameArtist;

        @BindView(R.id.btn_add_track)
        ImageView btnDeleteTrack;

        AlbumDetailHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnDeleteTrack.setImageResource(R.drawable.ic_more_vert);
        }

        void bind(Track track) {
            this.imgTrack.setImageResource(R.drawable.ic_audiotrack_orange);
            this.nameTrack.setText(track.getName());

            String artistName = track.getArtistName();
            this.nameArtist.setText(artistName);
        }

        @OnClick(R.id.cv_tracks)
        void onItemBtnClick() {
            itemClickListener.onItemClick(getTrackByPosition(getAdapterPosition()));
        }
    }

    public interface ItemClickListener {
        void onItemClick(Track track);
    }
}