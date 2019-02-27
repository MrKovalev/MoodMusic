package com.titanium.moodmusic.ui.fragments.trackDetail;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.data.model.tracks.Track;

import java.util.List;

public class TrackDetailFragment extends DialogFragment {

    private ImageView imgTrack;
    private TextView namePlayer;
    private TextView nameTrack;
    private SeekBar seekBarProgressTrack;
    private TextView textViewStartTime;
    private TextView textViewEndTime;
    private ImageButton btnPlay;

    private List<Track> trackList;
    private int position;


    public static TrackDetailFragment newInstance(String tracks, int position) {
        Bundle args = new Bundle();

        TrackDetailFragment fragment = new TrackDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.track_detail,container,false);

        imgTrack = view.findViewById(R.id.img_track);
        namePlayer = view.findViewById(R.id.name_player_detail);
        nameTrack = view.findViewById(R.id.name_track_detail);
        seekBarProgressTrack = view.findViewById(R.id.sb_time_progress_track);
        textViewStartTime = view.findViewById(R.id.txt_time_start);
        textViewEndTime = view.findViewById(R.id.txt_time_end);
        btnPlay = view.findViewById(R.id.ib_play);

        //call service for playing music

        return view;
    }
}
