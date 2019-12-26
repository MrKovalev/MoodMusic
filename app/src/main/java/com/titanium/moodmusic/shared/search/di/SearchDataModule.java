package com.titanium.moodmusic.shared.search.di;

import com.titanium.moodmusic.app.di.scopes.ApplicationScope;
import com.titanium.moodmusic.shared.search.data.datasources.SearchQueryDataSource;
import com.titanium.moodmusic.shared.search.data.datasources.SearchQueryDataSourceImpl;
import com.titanium.moodmusic.shared.search.data.repositories.SearchQueryRepositoryImpl;
import com.titanium.moodmusic.shared.search.domain.repositories.SearchQueryRepository;

import dagger.Binds;
import dagger.Module;

@Module
public interface SearchDataModule {

    @Binds
    SearchQueryDataSource<String> bindsSearchQueryDataSource(SearchQueryDataSourceImpl<String> dataSource);

    @Binds
    SearchQueryRepository<String> bindsSearchQueryRepository(SearchQueryRepositoryImpl<String> searchQueryRepository);
}
