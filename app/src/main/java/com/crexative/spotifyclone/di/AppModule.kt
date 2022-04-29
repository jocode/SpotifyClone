package com.crexative.spotifyclone.di

import android.util.Log
import com.crexative.spotifyclone.core.AppPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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

private const val BASE_URL = "https://api.spotify.com/"
private const val TAG: String = "OkHTTP"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor = HttpLoggingInterceptor { message ->
        Log.d(TAG, message)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(HttpLoggingInterceptor())
        okHttpBuilder.addInterceptor(Interceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${AppPreferences.token}")
                .build()
            chain.proceed(newRequest)
        })
        okHttpBuilder.addInterceptor(interceptor)
        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}