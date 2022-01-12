package com.example.repository.product

import com.example.local.mappers.ProductDaoMapper
import com.example.local.roomDatabase.ProductDao
import com.example.local.roomDatabase.ProductDaoModel
import com.example.models.dtoModels.requestModels.ProductRequestModel
import com.example.models.dtoModels.responseModels.ProductResponseModel
import com.example.network.apiServices.ProductApiService
import com.example.network.mappers.ProductDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
     val productApiService: ProductApiService,
     val productDao: ProductDao,
) {

    suspend fun getProducts(): Flow<ProductResponseModel> =
        flow {
            val response = productApiService.getProducts(ProductRequestModel("{productSearch(size: 20) {products {id name photo(size: LARGE) { url } vendor { name } weight price rating { rating count: signals } } } }"))
            emit(response)
            val daoResponse = ProductDaoMapper.mapFromEntityToDaoList(ProductDtoMapper.mapFromDtoToEntity(response))
            saveProductsToDatabase(daoResponse)
        }


    private suspend fun saveProductsToDatabase(productList: List<ProductDaoModel>?) {
        productList?.forEach {
            productDao.saveProduct(it)
        }
    }

    suspend fun getProductsFromDatabase(): Flow<List<ProductDaoModel>?> =
        flow {
            emit(productDao.getAllProducts())
        }

}