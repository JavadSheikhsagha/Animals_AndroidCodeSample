package com.example.repository.di

import com.example.local.roomDatabase.dao.AnimalDao
import com.example.local.roomDatabase.dao.AnimalDaoImpl
import com.example.network.apiServices.AnimalApiService
import com.example.network.apiServices.AnimalApiServiceImpl
import com.example.repository.animal.AnimalRepository
import com.example.repository.animal.AnimalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun getRepository(animalApiService: AnimalApiServiceImpl,animalDao: AnimalDaoImpl): AnimalRepository {
        return AnimalRepositoryImpl(animalApiService,animalDao)
    }
}