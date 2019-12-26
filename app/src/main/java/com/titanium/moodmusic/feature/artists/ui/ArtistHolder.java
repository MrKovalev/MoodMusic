package com.titanium.moodmusic.feature.artists.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.app.utils.ImageLoadUtils;
import com.titanium.moodmusic.feature.artists.domain.entities.Artist;

import butterknife.BindView;
import butterknife.ButterKnife;

class ArtistHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_artist)
    ImageView artistsImageView;

    @BindView(R.id.txt_artist_name)
    TextView artistsName;

    ArtistHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(Artist artist) {
        ImageLoadUtils.loadImage(
                itemView.getContext(),
                artist.getDefaultImageUrl(),
                R.mipmap.noavatar,
                this.artistsImageView
        );

        this.artistsName.setText(artist.getName());
    }
}
