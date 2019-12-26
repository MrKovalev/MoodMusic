package com.titanium.moodmusic.feature.main;

import com.titanium.moodmusic.component.presentation.BasePresenter;

import javax.inject.Inject;

import moxy.InjectViewState;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    public MainPresenter() {
    }

    public void onShareClicked() {
        getViewState().shareApp();
    }

    public void onRateAppClicked() {
        getViewState().rateApp();
    }
}
