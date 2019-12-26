package com.titanium.moodmusic.feature.main;

import com.titanium.moodmusic.component.presentation.BaseView;

import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends BaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void shareApp();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void rateApp();
}
