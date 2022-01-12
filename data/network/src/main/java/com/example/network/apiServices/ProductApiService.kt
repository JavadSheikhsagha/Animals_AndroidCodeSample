package com.example.network.apiServices

import com.example.models.dtoModels.requestModels.ProductRequestModel
import com.example.models.dtoModels.responseModels.ProductResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductApiService {

    @POST("/user")
    suspend fun getProducts(
        @Body productRequestModel: ProductRequestModel
    ) : ProductResponseModel
}