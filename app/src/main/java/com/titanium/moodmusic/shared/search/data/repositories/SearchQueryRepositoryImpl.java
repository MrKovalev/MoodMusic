package com.titanium.moodmusic.shared.search.data.repositories;

import com.titanium.moodmusic.shared.search.data.datasources.SearchQueryDataSource;
import com.titanium.moodmusic.shared.search.domain.repositories.SearchQueryRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchQueryRepositoryImpl<T> implements SearchQueryRepository<T> {

    private final SearchQueryDataSource<T> searchQueryDataSource;

    @Inject
    public SearchQueryRepositoryImpl(SearchQueryDataSource<T> searchQueryDataSource) {
        this.searchQueryDataSource = searchQueryDataSource;
    }

    @Override
    public Observable<T> getObservable() {
        return searchQueryDataSource.getObservable();
    }

    @Override
    public void add(T query) {
        searchQueryDataSource.add(query);
    }
}
