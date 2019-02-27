package com.titanium.moodmusic.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.data.model.artists.Artist;
import com.titanium.moodmusic.data.model.tracks.Track;
import com.titanium.moodmusic.utils.ImageLoadUtils;

import java.util.ArrayList;
import java.util.List;

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistsHolder> {

    private List<Artist> artistList = new ArrayList<>();
    private ItemArtistClickListener artistItemClickListener;
    private Context context;

    public ArtistsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ArtistsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.artist_item, viewGroup, false);
        return new ArtistsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistsHolder artistsHolder, int i) {
        final Artist itemArtist = artistList.get(i);
        ImageLoadUtils.loadImage(context,itemArtist.getImageUrl()
                ,R.mipmap.noavatar,artistsHolder.artistsImageView);
        artistsHolder.artistsName.setText(itemArtist.getName());

        artistsHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artistItemClickListener.onItemClick(itemArtist);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (artistList != null){
            return artistList.size();
        }
        return 0;
    }

    public void setArtistList(List<Artist> artistList){
        for(Artist artist : artistList){
            if (checkUniqueArtist(artist))
                this.artistList.add(artist);
        }
        notifyDataSetChanged();
    }

    public void setArtist(Artist artist){
        this.artistList.add(artist);
        notifyItemChanged(getItemCount() - 1);
    }

    public Artist getArtistByPosition(int position){
        return artistList.get(position);
    }

    public void clearArtistList(){
        if (artistList != null){
            artistList.clear();
            notifyDataSetChanged();
        }
    }

    private boolean checkUniqueArtist(Artist artistForAnalyse){
        for (Artist artist : this.artistList){
            if (artistForAnalyse.getMbid().equalsIgnoreCase(artist.getMbid()))
                return false;
        }

        return true;
    }

    public void setArtistItemClickListener(ItemArtistClickListener itemClickListener){
        this.artistItemClickListener = itemClickListener;
    }

     static class ArtistsHolder extends RecyclerView.ViewHolder{

        ImageView artistsImageView;
        TextView artistsName;

         ArtistsHolder(@NonNull View itemView) {
            super(itemView);
            artistsImageView = itemView.findViewById(R.id.img_artist);
            artistsName = itemView.findViewById(R.id.txt_artist_name);
        }
    }

    public interface ItemArtistClickListener{
        void onItemClick(Artist track);
    }
}
