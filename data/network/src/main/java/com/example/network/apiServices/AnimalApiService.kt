package com.example.network.apiServices

import com.example.models.dtoModels.responseModels.AnimalResponseModel
import retrofit2.http.POST
import retrofit2.http.Path

interface AnimalApiService {

    @POST("/animals/rand/{count}")
    suspend fun getAnimals(
        @Path("count")count:Int,
    ): List<AnimalResponseModel>
}