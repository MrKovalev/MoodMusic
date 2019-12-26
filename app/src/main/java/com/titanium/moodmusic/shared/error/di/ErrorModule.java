package com.titanium.moodmusic.shared.error.di;

import com.titanium.moodmusic.app.di.scopes.ApplicationScope;
import com.titanium.moodmusic.shared.error.handler.ErrorHandler;
import com.titanium.moodmusic.shared.error.handler.ErrorHandlerImpl;

import dagger.Binds;
import dagger.Module;

@Module
public interface ErrorModule {

    @Binds
    @ApplicationScope
    ErrorHandler bindsErrorHandler(ErrorHandlerImpl handler);
}
