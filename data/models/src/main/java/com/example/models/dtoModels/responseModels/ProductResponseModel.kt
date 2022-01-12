package com.example.models.dtoModels.responseModels

import com.example.entity.models.ErrorModel

data class ProductResponseModel(
    val data:ProductSearchResponseModel?,
    val errors:List<ErrorModel>?
) {
}

data class ProductDtoModel(
    val id:Long,
    val title:String,
    val boothTitle:String,
    val price:Long,
    val weight:Long,
    val rating:Double,
    val rateCount:Int,
)

data class ProductSearchResponseModel(
    val productSearch:ProductSearchModel
)

data class ProductSearchModel(
    val products:List<ProductDtoModel>
)