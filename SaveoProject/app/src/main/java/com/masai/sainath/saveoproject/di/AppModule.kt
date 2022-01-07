package com.masai.sainath.saveoproject.di

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.masai.sainath.saveoproject.data.remote.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val mContext: Context? = null
    private var mCache: Cache? = null

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(setupCache())
                .build())
            .build()
    }

    private fun setupCache(): Cache? {
        if (mCache == null) {
            try {
                mCache = Cache(
                    File(mContext?.cacheDir, "http-cache"),
                    10 * 1024 * 1024
                ) // 10 MB
            } catch (e: Exception) {
                Log.e(TAG, " Cache not Successful")
            }
        }
        return mCache
    }


    /**
     *providing api service instance for api calling
     */
    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

}