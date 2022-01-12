package com.example.network.mappers

import com.example.entity.models.ProductModel
import com.example.models.dtoModels.responseModels.ProductResponseModel

object ProductDtoMapper {

    fun mapFromDtoToEntity(productResponseModel: ProductResponseModel) : List<ProductModel>? {
        return productResponseModel.data?.productSearch?.products?.map {
            return@map ProductModel(
                it.id,
                it.title,
                it.boothTitle,
                it.price,
                it.weight,
                it.rating,
                it.rateCount
            )
        }
    }

    fun mapFromEntityToDto(productResponseModel: ProductResponseModel) : List<ProductModel>? {
        return productResponseModel.data?.productSearch?.products?.map {
            return@map ProductModel(
                it.id,
                it.title,
                it.boothTitle,
                it.price,
                it.weight,
                it.rating,
                it.rateCount
            )
        }
    }
}