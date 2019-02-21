package com.titanium.moodmusic.ui.fragments.artists;

import com.titanium.moodmusic.data.model.Artist;
import com.titanium.moodmusic.data.model.ArtistsResponce;

import java.util.List;
import retrofit2.Call;

public interface IArtistsInteractor {
    Call<ArtistsResponce> getTopChartArtists(int limit, String apiKey);
}
