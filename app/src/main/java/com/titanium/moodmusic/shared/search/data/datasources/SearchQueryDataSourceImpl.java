package com.titanium.moodmusic.shared.search.data.datasources;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class SearchQueryDataSourceImpl<T> implements SearchQueryDataSource<T> {

    private final PublishSubject<T> querySubject = PublishSubject.create();

    @Inject
    public SearchQueryDataSourceImpl() {
    }

    @Override
    public Observable<T> getObservable() {
        return querySubject;
    }

    @Override
    public void add(T query) {
        querySubject.onNext(query);
    }
}
