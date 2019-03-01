package com.titanium.moodmusic.ui.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.ui.adapters.MainPagerAdapter;
import com.titanium.moodmusic.ui.fragments.artists.ArtistsFragment;
import com.titanium.moodmusic.ui.fragments.tracks.TracksFragment;
import com.titanium.moodmusic.utils.RateUtils;
import com.titanium.moodmusic.utils.ShareUtils;

public class MusicActivity extends AppCompatActivity
                implements ArtistsFragment.onFragmentInteractionListener {

    private TabLayout tableLayout;
    private ViewPager viewPager;
    private MainPagerAdapter mainPagerAdapter;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.vp_main);
        tableLayout = findViewById(R.id.tl_main);

        setupToolbarAndNavigationMenu();
        initFragments();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.music_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_share:
                startActivity(ShareUtils.shareAppAction());
                break;
            case R.id.nav_rate:
                startActivity(RateUtils.findApplicationsForRate());
                break;
        }
        return false;
    }

    private void initFragments(){
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        tableLayout.setupWithViewPager(viewPager);
    }

    private void setupToolbarAndNavigationMenu(){
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(true);
    }

    @Override
    public void onFragmentInteraction(Bundle data) {
        TracksFragment fragment = (TracksFragment) (mainPagerAdapter.getCreatedFragment(MainPagerAdapter.TRACKS_IDX)).get();

        if (fragment != null)
            fragment.setBundle(data);
    }
}
