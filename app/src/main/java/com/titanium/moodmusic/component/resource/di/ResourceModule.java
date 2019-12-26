package com.titanium.moodmusic.component.resource.di;

import com.titanium.moodmusic.app.di.scopes.ApplicationScope;
import com.titanium.moodmusic.component.resource.data.provider.ResourceProvider;
import com.titanium.moodmusic.component.resource.data.provider.ResourceProviderImpl;

import dagger.Binds;
import dagger.Module;

@Module
public interface ResourceModule {

    @Binds
    @ApplicationScope
    ResourceProvider bindResourceProvider(ResourceProviderImpl provider);
}
