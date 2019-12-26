package com.titanium.moodmusic.shared.search.domain.repositories;

import io.reactivex.Observable;

public interface SearchQueryRepository<T> {

    Observable<T> getObservable();

    void add(T query);
}
