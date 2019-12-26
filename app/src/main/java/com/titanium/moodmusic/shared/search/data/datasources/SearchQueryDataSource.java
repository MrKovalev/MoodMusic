package com.titanium.moodmusic.shared.search.data.datasources;

import io.reactivex.Observable;

public interface SearchQueryDataSource<T> {

    Observable<T> getObservable();

    void add(T query);
}
