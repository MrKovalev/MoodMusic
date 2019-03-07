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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/** Класс отвечает за заполнение, отображение, взаимодействие,
 *  преобразование данных в RecyclerView исполнителей **/

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

        ImageLoadUtils.loadImage(context, itemArtist.getImageUrl()
                , R.mipmap.noavatar, artistsHolder.artistsImageView);
        artistsHolder.artistsName.setText(itemArtist.getName());
    }

    @Override
    public int getItemCount() {
        if (artistList != null) {
            return artistList.size();
        }
        return 0;
    }

    public void setArtistList(List<Artist> artistList) {
        for (Artist artist : artistList) {
            if (checkUniqueArtist(artist))
                this.artistList.add(artist);
        }
        notifyDataSetChanged();
    }

    public void setArtist(Artist artist) {
        this.artistList.add(artist);
        notifyItemInserted(getItemCount() + 1);
    }

    public Artist getArtistByPosition(int position) {
        return artistList.get(position);
    }

    public void clearArtistList() {
        if (artistList != null) {
            artistList.clear();
            notifyDataSetChanged();
        }
    }

    private boolean checkUniqueArtist(Artist artistForAnalyse) {
        for (Artist artist : this.artistList) {
            if (artistForAnalyse.getMbid().equalsIgnoreCase(artist.getMbid()))
                return false;
        }

        return true;
    }

    public void setArtistItemClickListener(ItemArtistClickListener itemClickListener) {
        this.artistItemClickListener = itemClickListener;
    }

    class ArtistsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_artist)
        ImageView artistsImageView;
        @BindView(R.id.txt_artist_name)
        TextView artistsName;

        ArtistsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.cv_artist)
        void onItemClicked() {
            if (artistItemClickListener != null)
                artistItemClickListener.onItemClick(getArtistByPosition(getAdapterPosition()));
        }
    }

    public interface ItemArtistClickListener {
        void onItemClick(Artist artist);
    }
}
