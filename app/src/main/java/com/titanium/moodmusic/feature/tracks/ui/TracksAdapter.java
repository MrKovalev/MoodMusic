package com.titanium.moodmusic.feature.tracks.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.component.common.Loadable;
import com.titanium.moodmusic.shared.loading.LoadingHolder;
import com.titanium.moodmusic.shared.tracks.Track;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TracksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TRACK_ITEM_TYPE = 1;
    private static final int LOADING_ITEM_TYPE = 2;

    private final List<Track> tracks = new ArrayList<>();
    private final ItemTrackClickListener trackItemClickListener;
    private final ItemTrackBtnAddClickListener trackBtnAddClickListener;

    private final Loadable loadable;
    private boolean isLoadingItemVisible = false;

    TracksAdapter(ItemTrackClickListener trackItemClickListener, ItemTrackBtnAddClickListener trackBtnAddClickListener, Loadable loadable) {
        this.trackItemClickListener = trackItemClickListener;
        this.trackBtnAddClickListener = trackBtnAddClickListener;
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
            return TRACK_ITEM_TYPE;
        }
    }

    private int getLoadingItemPosition() {
        return tracks.size() - 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case TRACK_ITEM_TYPE:
                View artist = inflater.inflate(R.layout.item_track, viewGroup, false);
                return new TracksHolder(artist);
            case LOADING_ITEM_TYPE:
                View loader = inflater.inflate(R.layout.item_loading, viewGroup, false);
                return new LoadingHolder(loader);
            default:
                throw new IllegalArgumentException("Unknown holder type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int i) {
        final Track track = tracks.get(i);

        if (holder instanceof TracksHolder) {
            ((TracksHolder) holder).bind(track);
        } else if (holder instanceof LoadingHolder) {
            loadable.onLoad();
        } else {
            throw new IllegalArgumentException("Unknown holder type");
        }
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    @Override
    public long getItemId(int position) {
        return tracks.get(position).hashCode();
    }

    public void setTracks(List<Track> tracks) {
        int beforeSize = getItemCount();

        this.tracks.addAll(tracks);

        int afterSize = getItemCount();

        if (afterSize > beforeSize) {
            notifyItemRangeChanged(beforeSize, afterSize);
        }
    }

    public void setTrack(Track trackModel) {
        this.tracks.add(trackModel);
        notifyItemInserted(getItemCount() + 1);
    }

    private Track getTrackByPosition(int position) {
        return tracks.get(position);

    }

    void clearTracksList() {
        isLoadingItemVisible = false;
        tracks.clear();

        notifyDataSetChanged();
    }

    class TracksHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_track)
        ImageView imgTrack;

        @BindView(R.id.name_track)
        TextView nameTrack;

        @BindView(R.id.name_artist)
        TextView nameArtist;

        @BindView(R.id.btn_add_track)
        ImageView btnAddTrackToAlbum;

        TracksHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Track track) {
            this.nameTrack.setText(track.getName());

            String artistName = track.getArtistName();
            this.nameArtist.setText(artistName);
        }

        @OnClick(R.id.cv_tracks)
        void onItemClicked() {
            trackItemClickListener.onItemClick(getTrackByPosition(getAdapterPosition()));
        }

        @OnClick(R.id.btn_add_track)
        void onItemBtnClick() {
            trackBtnAddClickListener.onItemClick(getTrackByPosition(getAdapterPosition()));
        }
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

    public interface ItemTrackClickListener {
        void onItemClick(Track track);
    }

    public interface ItemTrackBtnAddClickListener {
        void onItemClick(Track track);
    }
}
