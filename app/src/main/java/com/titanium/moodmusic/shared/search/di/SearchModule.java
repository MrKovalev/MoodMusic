package com.titanium.moodmusic.shared.search.di;

import com.titanium.moodmusic.shared.search.domain.usecases.SearchQueryInteractor;
import com.titanium.moodmusic.shared.search.domain.usecases.SearchQueryInteractorImpl;

import dagger.Binds;
import dagger.Module;

@Module
public interface SearchModule {

    @Binds
    SearchQueryInteractor<String> bindsSearchQueryInteractor(SearchQueryInteractorImpl<String> searchQueryInteractor);
}