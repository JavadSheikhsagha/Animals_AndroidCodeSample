package com.example.local.di

import android.content.Context
import androidx.room.Room
import com.example.local.roomDatabase.ProductDao
import com.example.local.roomDatabase.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideProductDatabase(
        @ApplicationContext context: Context
    ): ProductDao = Room.databaseBuilder(
        context,
        ProductDatabase::class.java,
        "products")
        .build()
        .productDao()
}