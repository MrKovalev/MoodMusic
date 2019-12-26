package com.titanium.moodmusic.shared.search.domain.usecases;

import com.titanium.moodmusic.shared.search.domain.repositories.SearchQueryRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchQueryInteractorImpl<T> implements SearchQueryInteractor<T> {

    private final SearchQueryRepository<T> searchQueryRepository;
    private static final long timer = 500L;

    @Inject
    public SearchQueryInteractorImpl(SearchQueryRepository<T> searchQueryRepository) {
        this.searchQueryRepository = searchQueryRepository;
    }

    @Override
    public Observable<T> getObservable() {
        return searchQueryRepository.getObservable()
                .debounce(timer, TimeUnit.MILLISECONDS);
    }

    @Override
    public void add(T query) {
        searchQueryRepository.add(query);
    }
}
