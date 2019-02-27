package com.titanium.moodmusic.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.data.model.tracks.Track;
import com.titanium.moodmusic.data.model.artists.Artist;

import java.util.ArrayList;
import java.util.List;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.TracksHolder> {

    private List<Track> trackList = new ArrayList<>();
    private Context context;
    private ItemTrackClickListener trackItemClickListener;

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
    public void onBindViewHolder(@NonNull TracksHolder tracksHolder, final int i) {
        final Track itemTrack = trackList.get(i);
        tracksHolder.imgTrack.setImageResource(R.mipmap.play);
        tracksHolder.nameTrack.setText(itemTrack.getName());

        if (itemTrack.getArtist() instanceof String){
            String artistName = (String) itemTrack.getArtist();
            Log.d("TAG", "GENERIC name privitive = "+artistName);
            tracksHolder.nameArtist.setText(artistName);
        } else {
            Artist artist = (Artist) itemTrack.getArtist();
            Log.d("TAG", "GENERIC name compl = "+artist.getName());
            tracksHolder.nameArtist.setText(artist.getName());
        }

        tracksHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trackItemClickListener != null)
                    trackItemClickListener.onItemClick(trackList, itemTrack, i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (trackList != null){
            return trackList.size();
        }

        return 0;
    }

    public void setTrackList(List<Track> trackList){
        for(Track track : trackList){
            if(checkUniqueTrack(track))
                this.trackList.add(track);
        }
        notifyDataSetChanged();
    }

    public void setTrack(Track track){
        this.trackList.add(track);
        notifyItemChanged(getItemCount() - 1);
    }

    public void clearArtistList(){
        if (trackList != null){
            trackList.clear();
            notifyDataSetChanged();
        }
    }

    private boolean checkUniqueTrack(Track track){
        for (Track trackForAnalyse : this.trackList){
            if (trackForAnalyse.getName().equalsIgnoreCase(track.getName()))
                return false;
        }

        return true;
    }

    public void setTrackItemClickListener(ItemTrackClickListener itemClickListener){
        this.trackItemClickListener = itemClickListener;
    }

     static class TracksHolder extends RecyclerView.ViewHolder{

        private ImageView imgTrack;
        private TextView nameTrack;
        private TextView nameArtist;

         TracksHolder(@NonNull View itemView) {
            super(itemView);
            imgTrack = itemView.findViewById(R.id.img_track);
            nameTrack = itemView.findViewById(R.id.name_track);
            nameArtist = itemView.findViewById(R.id.name_artist);
        }
    }

    public interface ItemTrackClickListener{
        void onItemClick(List<Track> trackList, Track track, int position);
    }
}
