package com.example.network.di

import com.example.network.apiServices.ProductApiService
import com.example.network.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//        return okHttpClient
//    }

    @Provides
    @Singleton
    fun provideRetrofit(@BASE_URL baseUrl: String): ProductApiService {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProductApiService::class.java)

    }

    @Provides
    @Singleton
    @BASE_URL
    fun provideBaseUrl() = "https://api.basalam.com/api/"
}