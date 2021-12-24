package com.ibrajix.eplfootball.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ibrajix.eplfootball.BuildConfig
import com.ibrajix.eplfootball.network.ApiDataSource
import com.ibrajix.eplfootball.network.ApiService
import com.ibrajix.eplfootball.network.EndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesBaseUrl() = EndPoints.BASE_URL

    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(EndPoints.BASE_URL)
        .client(
            OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                if (BuildConfig.DEBUG) {
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                }
                client.addInterceptor(logging)
                client.connectTimeout(120, TimeUnit.SECONDS)
                client.readTimeout(120, TimeUnit.SECONDS)
                client.protocols(Collections.singletonList(Protocol.HTTP_1_1))
            }.build()
        )
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiDataSource(apiService: ApiService) = ApiDataSource(apiService)

}