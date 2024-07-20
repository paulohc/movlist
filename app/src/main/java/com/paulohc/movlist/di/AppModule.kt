package com.paulohc.movlist.di

import android.content.Context
import com.paulohc.movlist.common.Constants
import com.paulohc.movlist.data.remote.AuthInterceptor
import com.paulohc.movlist.data.remote.MovieApi
import com.paulohc.movlist.data.repository.MovieRepositoryImpl
import com.paulohc.movlist.domain.repository.MovieRepository
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return AuthInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        interceptor: Interceptor,
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val cache = Cache(context.cacheDir, cacheSize)
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(
        retrofit: Retrofit,
    ): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApi: MovieApi,
    ): MovieRepository {
        return MovieRepositoryImpl(movieApi = movieApi)
    }
}
