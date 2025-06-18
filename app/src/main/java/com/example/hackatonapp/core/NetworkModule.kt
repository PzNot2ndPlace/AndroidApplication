package com.example.hackatonapp.core

import android.content.Context
import com.example.hackatonapp.local.TokenDataStore
import com.example.hackatonapp.network.Api
import com.example.hackatonapp.network.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) = TokenDataStore(context)

    @Provides
    @Singleton
    fun provideInterceptor(ds : TokenDataStore) = AuthInterceptor(tokenDataStore = ds)

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context, interceptor: AuthInterceptor) = Retrofit.Builder()
        .baseUrl("http://45.141.78.50:8080/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
}