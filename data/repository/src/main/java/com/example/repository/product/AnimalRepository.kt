package com.example.repository.product

import com.example.entity.models.AnimalModel
import com.example.local.mappers.AnimalDaoMapper
import com.example.local.roomDatabase.dao.AnimalDao_ImplFile
import com.example.local.roomDatabase.model.AnimalDaoModel
import com.example.network.apiServices.AnimalApiService_Impl
import com.example.network.mappers.AnimalDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimalRepository @Inject constructor(
    val animalApiService: AnimalApiService_Impl,
    val animalDao: AnimalDao_ImplFile,
) {

    suspend fun getAnimals(): Flow<List<AnimalModel>?> =
        flow {
            if (AnimalDaoMapper.mapFromDaoToEntityList(animalDao.getAllAnimals()) != null
            )
                emit(AnimalDaoMapper.mapFromDaoToEntityList(animalDao.getAllAnimals())!!)

            val serverAnimals = AnimalDtoMapper.mapFromDtoToEntity(animalApiService.getAnimals(10))
            saveAnimalsToDatabase(AnimalDaoMapper.mapFromEntityToDaoList(serverAnimals))

            if (AnimalDaoMapper.mapFromDaoToEntityList(animalDao.getAllAnimals()) != null
            )
                emit(AnimalDaoMapper.mapFromDaoToEntityList(animalDao.getAllAnimals())!!)
        }


    private suspend fun saveAnimalsToDatabase(animalList: List<AnimalDaoModel>?) {
        animalList?.forEach {
            animalDao.saveAnimal(it)
        }
    }

}