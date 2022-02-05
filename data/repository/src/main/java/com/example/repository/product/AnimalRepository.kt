package com.example.repository.product

import androidx.lifecycle.MutableLiveData
import com.example.entity.models.AnimalModel
import com.example.entity.models.MessageModel
import com.example.local.mappers.AnimalDaoMapper
import com.example.local.roomDatabase.dao.AnimalDao_ImplFile
import com.example.local.roomDatabase.model.AnimalDaoModel
import com.example.network.apiServices.AnimalApiService_Impl
import com.example.network.mappers.AnimalDtoMapper
import com.example.presentation.products.networkBoundResource
import com.example.repository.utils.ErrorCallback
import com.example.repository.utils.ErrorType
import com.example.repository.utils.SafeApiCall
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimalRepository @Inject constructor(
    val animalApiService: AnimalApiService_Impl,
    val animalDao: AnimalDao_ImplFile,
) : ErrorCallback {

    val otherErrorLV = MutableLiveData<ErrorType>()
    val networkErrorLV = MutableLiveData<MessageModel>()

    fun getAnimals() = networkBoundResource(
        query = {
            animalDao.getAllAnimals()
        },
        fetch = {
            delay(2000)
            animalApiService.getAnimals(5)
        },
        saveFetchResult = { animals ->
            withContext(Dispatchers.Main){
                animalDao.deleteAllAnimals()
                AnimalDtoMapper.mapFromDtoToEntity(animals).forEach {
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