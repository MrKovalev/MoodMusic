package com.titanium.moodmusic.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.titanium.moodmusic.R;
import com.titanium.moodmusic.component.ui.BaseActivity;
import com.titanium.moodmusic.feature.albums.ui.AlbumsFragment;
import com.titanium.moodmusic.feature.artists.ui.ArtistsFragment;
import com.titanium.moodmusic.feature.main.MainPagerAdapter;
import com.titanium.moodmusic.feature.main.MainPresenter;
import com.titanium.moodmusic.feature.main.MainView;
import com.titanium.moodmusic.feature.tracks.ui.TracksFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.presenter.InjectPresenter;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.tl_main)
    TabLayout tableLayout;

    @BindView(R.id.vp_main)
    ViewPager viewPager;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @InjectPresenter
    public MainPresenter mainPresenter;

    private final MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.appComponent.musicActivityComponent().build().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupToolbarAndNavigationMenu();

        initFragments();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_share:
                mainPresenter.onShareClicked();
                break;
            case R.id.nav_rate:
                mainPresenter.onRateAppClicked();
                break;
        }
        return false;
    }

    @Override
    public void shareApp() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

        sharingIntent.setType("text/plain");
        String shareBody = this.getString(R.string.share_body);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

        Intent intent = Intent.createChooser(sharingIntent, this.getString(R.string.share_title));
        startActivity(intent);
    }

    @Override
    public void rateApp() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.getString(R.string.market_url)));
        startActivity(intent);
    }

    private void initFragments() {
        viewPager.setAdapter(mainPagerAdapter);

        mainPagerAdapter.addTab(this.getString(R.string.tab_artists_title), ArtistsFragment.newInstance());
        mainPagerAdapter.addTab(this.getString(R.string.tab_tracks_title), TracksFragment.newInstance());
        mainPagerAdapter.addTab(this.getString(R.string.tab_albums_title), AlbumsFragment.newInstance());

        tableLayout.setupWithViewPager(viewPager);
    }

    private void setupToolbarAndNavigationMenu() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(true);
    }
}
