package com.titanium.moodmusic.shared.search.domain.usecases;

import io.reactivex.Observable;

public interface SearchQueryInteractor<T> {

    Observable<T> getObservable();

    void add(T query);
}
