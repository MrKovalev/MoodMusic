package com.titanium.moodmusic.feature.main;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<PagerTab> fragments = new ArrayList<>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position).getFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addTab(String title, Fragment fragment) {
        fragments.add(new PagerTab(title, fragment));
        notifyDataSetChanged();
    }

    private class PagerTab {

        private final String title;
        private final Fragment fragment;


        PagerTab(String title, Fragment fragment) {
            this.title = title;
            this.fragment = fragment;
        }

        String getTitle() {
            return title;
        }

        Fragment getFragment() {
            return fragment;
        }
    }
}
