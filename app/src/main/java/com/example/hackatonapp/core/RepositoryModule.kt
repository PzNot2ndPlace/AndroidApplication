package com.example.hackatonapp.core

import com.example.hackatonapp.local.TokenDataStore
import com.example.hackatonapp.network.Api
import com.example.hackatonapp.network.AuthInterceptor
import com.example.hackatonapp.network.AuthRepository
import com.example.hackatonapp.network.LocationRepository
import com.example.hackatonapp.network.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @ViewModelScoped
    @Provides
    fun provideAuthRepository(api : Api, ds : TokenDataStore) : AuthRepository = AuthRepository(api, ds)

    @ViewModelScoped
    @Provides
    fun provideNoteRepository(api : Api) : NoteRepository = NoteRepository(api)

    @ViewModelScoped
    @Provides
    fun provideLocationRepository(api : Api) : LocationRepository = LocationRepository(api)
}