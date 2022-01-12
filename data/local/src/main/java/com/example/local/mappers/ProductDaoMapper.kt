package com.example.local.mappers

import com.example.entity.models.ProductModel
import com.example.local.roomDatabase.ProductDaoModel

object ProductDaoMapper {

    fun mapFromDaoToEntity(productDaoModel: ProductDaoModel) : ProductModel {
        return ProductModel(
            id = productDaoModel.uid,
            title = productDaoModel.title,
            boothTitle = productDaoModel.boothTitle,
            price = productDaoModel.price,
            weight = productDaoModel.weight,
            rating = productDaoModel.rating,
            rateCount = productDaoModel.rateCount
        )
    }

    fun mapFromEntityToDao(productModel: ProductModel): ProductDaoModel {
        return ProductDaoModel(
            uid = productModel.id,
            title = productModel.title,
            boothTitle = productModel.boothTitle,
            price = productModel.price,
            weight = productModel.weight,
            rating = productModel.rating,
            rateCount = productModel.rateCount
        )
    }

    fun mapFromEntityToDaoList(productModel: List<ProductModel>?) : List<ProductDaoModel>? {
        return productModel?.map {
            return@map mapFromEntityToDao(it)
        }
    }

    fun mapFromDaoToEntityList(productDaoModel: List<ProductDaoModel>?) : List<ProductModel>? {
        return productDaoModel?.map {
            return@map mapFromDaoToEntity(it)
        }
    }
}