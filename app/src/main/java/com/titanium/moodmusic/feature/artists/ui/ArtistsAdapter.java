package com.titanium.moodmusic.feature.artists.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.component.common.Loadable;
import com.titanium.moodmusic.feature.artists.domain.entities.Artist;
import com.titanium.moodmusic.shared.loading.LoadingHolder;

import java.util.ArrayList;
import java.util.List;

public class ArtistsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ARTIST_ITEM_TYPE = 1;
    private static final int LOADING_ITEM_TYPE = 2;

    private final List<Artist> artists = new ArrayList<>();
    private final Loadable loadable;
    private boolean isLoadingItemVisible = false;

    ArtistsAdapter(Loadable loadable) {
        this.loadable = loadable;

        setHasStableIds(true);
    }

    @Override
    public int getItemViewType(int position) {
        boolean isLoadingItem = position == getLoadingItemPosition()
                && isLoadingItemVisible;

        if (isLoadingItem) {
            return LOADING_ITEM_TYPE;
        } else {
            return ARTIST_ITEM_TYPE;
        }
    }

    private int getLoadingItemPosition() {
        return artists.size() - 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case ARTIST_ITEM_TYPE:
                View artist = inflater.inflate(R.layout.item_artist, viewGroup, false);
                return new ArtistHolder(artist);
            case LOADING_ITEM_TYPE:
                View loader = inflater.inflate(R.layout.item_loading, viewGroup, false);
                return new LoadingHolder(loader);
            default:
                throw new IllegalArgumentException("Unknown holder type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        final Artist itemArtist = artists.get(i);

        if (holder instanceof ArtistHolder) {
            ((ArtistHolder) holder).bind(itemArtist);
        } else if (holder instanceof LoadingHolder) {
            loadable.onLoad();
        } else {
            throw new IllegalArgumentException("Unknown holder type");
        }
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    @Override
    public long getItemId(int position) {
        Artist artist = artists.get(position);
        return artist.hashCode();
    }

    void setArtists(List<Artist> artists) {
        int beforeSize = getItemCount();

        this.artists.addAll(artists);

        int afterSize = getItemCount();

        if (afterSize > beforeSize) {
            notifyItemRangeChanged(beforeSize, afterSize);
        }
    }

    void clearArtistList() {
        isLoadingItemVisible = false;
        artists.clear();

        notifyDataSetChanged();
    }

    void setLoadingItem() {
        if (!isLoadingItemVisible) {
            int position = getLoadingItemPosition();
            notifyItemInserted(position);
        }

        isLoadingItemVisible = true;
    }

    void removeLoadingItem() {
        if (isLoadingItemVisible) {
            int position = getLoadingItemPosition();
            notifyItemRemoved(position);
        }

        isLoadingItemVisible = false;
    }
}
