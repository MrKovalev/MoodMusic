package com.titanium.moodmusic.component.presentation;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import moxy.MvpPresenter;

public abstract class BasePresenter<T extends BaseView> extends MvpPresenter<T> {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void unsubscribeOnDestroy(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }
}
