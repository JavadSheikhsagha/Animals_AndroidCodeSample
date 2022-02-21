package com.example.network.apiServices

import com.example.models.dtoModels.responseModels.AnimalResponseModel
import javax.inject.Inject

class AnimalApiServiceImpl @Inject constructor(private val apiService: AnimalApiService) {

    suspend fun getAnimals(limit:Int) : List<AnimalResponseModel>{
        return apiService.getAnimals(limit)
    }

}