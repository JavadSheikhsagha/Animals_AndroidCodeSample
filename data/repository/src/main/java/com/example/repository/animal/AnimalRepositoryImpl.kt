package com.example.repository.animal

import androidx.lifecycle.MutableLiveData
import com.example.entity.models.AnimalModel
import com.example.entity.models.MessageModel
import com.example.local.mappers.AnimalDaoMapper
import com.example.local.roomDatabase.dao.AnimalDaoImpl
import com.example.network.apiServices.AnimalApiServiceImpl
import com.example.network.mappers.AnimalDtoMapper
import com.example.presentation.products.networkBoundResource
import com.example.repository.utils.ErrorCallback
import com.example.repository.utils.ErrorType
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(
    private val animalApiService: AnimalApiServiceImpl,
    private val animalDao: AnimalDaoImpl,
) : ErrorCallback, AnimalRepository {

    val otherErrorLV = MutableLiveData<ErrorType>()
    val networkErrorLV = MutableLiveData<MessageModel>()

    override fun getAnimals() = networkBoundResource(
        query = {
                animalDao.getAllAnimals().map { AnimalDaoMapper.mapFromDaoToEntityList(it) }
//            animalDao.getAllAnimals()
        },
        fetch = {
            delay(2000)
            animalApiService.getAnimals(5).map { AnimalDtoMapper.mapFromDtoToEntity(it) }
        },
        saveFetchResult = { animals ->
            withContext(Dispatchers.Main){
                animalDao.deleteAllAnimals()
                animals.forEach {
                    animalDao.saveAnimal(AnimalDaoMapper.mapFromEntityToDao(it))
                }
            }
        }, {
            true
        }, this
    )

    override fun onError(body: MessageModel) {
        networkErrorLV.postValue(body)
    }

    override fun onError(errorType: ErrorType) {
        otherErrorLV.postValue(errorType)
    }

}