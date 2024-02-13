package com.paulohc.movlist.data.remote

import com.paulohc.movlist.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader(
            name = "Authorization",
            value = "Bearer ${BuildConfig.TMDB_API_TOKEN}",
        )

        return chain.proceed(requestBuilder.build())
    }
}
