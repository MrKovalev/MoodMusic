package com.titanium.moodmusic.ui.fragments.tracks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.titanium.moodmusic.R;
import com.titanium.moodmusic.data.api.Constants;
import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;
import com.titanium.moodmusic.data.model.tracks.Track;
import com.titanium.moodmusic.di.components.DaggerTracksComponent;
import com.titanium.moodmusic.di.modules.activity_level.TracksAdapterModule;
import com.titanium.moodmusic.di.modules.activity_level.TracksPresenterModule;
import com.titanium.moodmusic.ui.adapters.TracksAdapter;
import com.titanium.moodmusic.ui.fragments.BaseFragment;
import com.titanium.moodmusic.ui.fragments.trackDetailWeb.WebFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** Класс отвечает за отображение данных, взаимодействие с пользователем **/

public class TracksFragment extends BaseFragment
        implements ITracksView
        , SearchView.OnQueryTextListener
        , SearchView.OnCloseListener {

    public static final String EXTRA_CAN_SHOW_TRACKS_BY_ARTIST = "EXTRA_CAN_SHOW_TRACKS_BY_ARTIST";
    public static final String EXTRA_ARIST_NAME = "EXTRA_ARIST_NAME";

    @BindView(R.id.recycler_tracks)
    RecyclerView tracksRecyclerView;
    SearchView searchView;
    @BindView(R.id.progress_tracks)
    ProgressBar progressBarMain;

    @Inject
    ITracksPresenter tracksPresenter;
    @Inject
    TracksAdapter tracksAdapter;

    private Unbinder unbinder;

    private onFragmentTrackGetAlbumsInteractionListener onFragmentTrackInteractionListener;
    private onFragmentTrackAddToAlbumInteractionListener onFragmentTrackAddToAlbumInteractionListener;

    private boolean isLoading, isSearchingNow;
    private int currentPageTopChartTracks = Constants.DEFAULT_PAGE_CHART;
    private int currentPageSearch = Constants.DEFAULT_PAGE_CHART;

    private Bundle bundle;

    public TracksFragment() {
    }

    public static TracksFragment newInstance() {
        return new TracksFragment();
    }

    //вызывается только в случае, если мы просматриваем треки конкретного артиста из 1 фрагмента
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            if (this.bundle != null) {
                boolean canLoadTracksByArtist = bundle.getBoolean(EXTRA_CAN_SHOW_TRACKS_BY_ARTIST);
                String nameArtist = bundle.getString(EXTRA_ARIST_NAME);

                if (canLoadTracksByArtist) {
                    isSearchingNow = true;
                    tracksPresenter.searchTracksByArtist(nameArtist, ""
                            , Constants.TOP_ITEMS_LIMIT_SEARCH, Constants.DEFAULT_PAGE_SEARCH, Constants.API_KEY);
                    setBundle(null);
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            onFragmentTrackInteractionListener = (onFragmentTrackGetAlbumsInteractionListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            onFragmentTrackAddToAlbumInteractionListener = (onFragmentTrackAddToAlbumInteractionListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        DaggerTracksComponent.builder()
                .tracksAdapterModule(new TracksAdapterModule(getContext()))
                .tracksPresenterModule(new TracksPresenterModule(this))
                .build()
                .inject(this);

        tracksAdapter.setTrackItemClickListener(new TracksAdapter.ItemTrackClickListener() {
            @Override
            public void onItemClick(List<Track> trackList, Track track, int position) {
                tracksPresenter.openTrackDetail(trackList, track, position);
            }
        });

        tracksAdapter.setTrackBtnAddClickListener(new TracksAdapter.ItemTrackBtnAddClickListener() {
            @Override
            public void onItemClick(Track track) {
                prepareAddTrackToAlbumDialog(track);
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem menuItem = menu.findItem(R.id.search_field);

        if (menuItem != null) {
            searchView = (SearchView) menuItem.getActionView();
            searchView.setOnQueryTextListener(this);
            searchView.setOnCloseListener(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tracks_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        tracksRecyclerView.setLayoutManager(linearLayoutManager);
        tracksRecyclerView.setAdapter(tracksAdapter);
        tracksRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    if (!isSearchingNow) {
                        int totalItemCount = linearLayoutManager.getItemCount();
                        int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                        if (!isLoading) {
                            if ((totalItemCount - 1) == lastVisibleItem) {
                                currentPageTopChartTracks++;
                                isLoading = true;
                                tracksPresenter.getTopChartTracks(currentPageTopChartTracks, Constants.TOP_ITEMS_LIMIT_CHART, Constants.API_KEY);
                            }
                        }
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tracksPresenter.getTopChartTracks(Constants.DEFAULT_PAGE_SEARCH, Constants.TOP_ITEMS_LIMIT_CHART, Constants.API_KEY);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tracksPresenter.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentTrackInteractionListener = null;
        onFragmentTrackAddToAlbumInteractionListener = null;
    }


    @Override
    protected void search(String query) {
        isSearchingNow = true;

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null)
            fragmentManager.popBackStack();

        tracksPresenter.searchTrack(Constants.TOP_ITEMS_LIMIT_SEARCH,
                Constants.DEFAULT_PAGE_SEARCH, query, null, Constants.API_KEY);
    }

    @Override
    public void showProgress() {
        progressBarMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBarMain.setVisibility(View.GONE);
    }

    @Override
    public void loadTracks(List<Track> trackList) {
        if (tracksAdapter != null) {
            isLoading = false;
            tracksAdapter.setTrackList(trackList);
        }
    }

    @Override
    public void searchTracks(List<Track> trackList) {
        if (tracksAdapter != null) {
            tracksAdapter.clearTracktList();
            tracksAdapter.setTrackList(trackList);
        }
    }

    @Override
    public void searchTracksByArtist(List<Track> trackList) {
        if (tracksAdapter != null) {
            tracksAdapter.clearTracktList();
            tracksAdapter.setTrackList(trackList);
        }
    }

    @Override
    public void openTrackDetail(List<Track> trackList, Track track, int position) {
        Fragment fragmentWebView = WebFragment.newInstance(track.getUrl());
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container_track, fragmentWebView);

        transaction.commit();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), getString(R.string.error_load), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onClose() {
        tracksAdapter.clearTracktList();
        tracksPresenter.getTopChartTracks(Constants.DEFAULT_PAGE_CHART, Constants.TOP_ITEMS_LIMIT_CHART, Constants.API_KEY);
        isSearchingNow = false;
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        search(newText);
        return false;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    private String setTracks(List<Track> trackList) {
        Gson gson = new GsonBuilder().create();
        Type trackType = new TypeToken<List<Track>>() {
        }.getType();
        return gson.toJson(trackList, trackType);
    }

    private boolean isUniqueAddingTrackToAlbum(Track newTrack, FavoriteAlbum favoriteAlbum) {
        for (Track track : favoriteAlbum.getTrackList()) {
            if (newTrack.getName().equalsIgnoreCase(track.getName()))
                return false;
        }

        return true;
    }

    private void prepareAddTrackToAlbumDialog(final Track track) {
        //получаем список альбомов из фрагмента альбомов
        final List<FavoriteAlbum> listAlbums = onFragmentTrackInteractionListener.onFragmentTrackGetAlbumsInteraction();

        //устанавливаем выбор
        List<String> listChoices = new ArrayList<>();
        for (FavoriteAlbum album : listAlbums) {
            listChoices.add(album.getNameAlbum());
        }

        String[] choices = new String[listChoices.size()];
        choices = listChoices.toArray(choices);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.choose_album_to_add_track));

        builder.setItems(choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isUniqueAddingTrackToAlbum(track, listAlbums.get(which))) {
                    onFragmentTrackAddToAlbumInteractionListener.onFragmentTrackAddToAlbumInteraction(track, which);
                    Toast.makeText(getContext(), "Трек успешно добавлен в альбом", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Данный трек уже добавлен в альбом!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    public interface onFragmentTrackGetAlbumsInteractionListener {
        List<FavoriteAlbum> onFragmentTrackGetAlbumsInteraction();
    }

    public interface onFragmentTrackAddToAlbumInteractionListener {
        void onFragmentTrackAddToAlbumInteraction(Track track, int positionAlbum);
    }
}
