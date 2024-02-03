package com.paulohc.movlist.di

import com.paulohc.movlist.network.MovieService
import com.paulohc.movlist.util.Constants
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideMovieService(): MovieService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .build()
        return retrofit.create(MovieService::class.java)
    }
}
