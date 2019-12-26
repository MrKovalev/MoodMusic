package com.titanium.moodmusic.app;

import android.app.Application;

import com.titanium.moodmusic.app.di.components.AppComponent;
import com.titanium.moodmusic.app.di.components.DaggerAppComponent;

public class App extends Application {

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .app(this)
                .context(getApplicationContext())
                .build();

        appComponent.inject(this);
    }
}
