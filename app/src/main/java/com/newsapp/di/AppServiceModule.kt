package com.newsapp.di

import com.google.gson.Gson
import com.newsapp.BuildConfig
import com.newsapp.data.source.remote.service.DutchNewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppServiceModule {
    const val VERSION = "v2"
    const val COUNTRY = "nl"
    const val APIKEY = "API_KEY"
    const val BASE_URL = "https://newsapi.org/$VERSION/top-headlines?country=$COUNTRY&apiKey=$APIKEY"

    @Singleton
    @Provides
    fun provideDutchNewsService(gson: Gson): DutchNewsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(DutchNewsService::class.java)
    }

    private fun createClient(): OkHttpClient {
        val httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }
        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            val requestBuilder: Request.Builder = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
            requestBuilder.build()
            chain.proceed(request)
        })
        return httpClient.build()
    }


}