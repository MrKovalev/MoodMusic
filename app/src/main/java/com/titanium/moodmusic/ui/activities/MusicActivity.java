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
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.titanium.moodmusic.R;
import com.titanium.moodmusic.ui.adapters.MainPagerAdapter;

public class MusicActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        drawerLayout = findViewById(R.id.drawer_settings);
        navigationView = findViewById(R.id.nav_settings);

        setupToolbarAndNavigationMenu();
        initFragments();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
        }

        if (drawerLayout != null){
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                    R.string.open_drawer, R.string.close_drawer);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }

        if (navigationView != null)
            navigationView.setNavigationItemSelectedListener(this);
    }

    public ViewPager getViewPager(){
        return this.viewPager;
    }
}
