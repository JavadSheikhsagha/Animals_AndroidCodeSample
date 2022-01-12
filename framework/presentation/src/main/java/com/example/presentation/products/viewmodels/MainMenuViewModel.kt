package com.example.presentation.products.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.entity.models.MessageModel
import com.example.local.mappers.ProductDaoMapper
import com.example.network.mappers.ProductDtoMapper
import com.example.repository.product.ProductRepository
import com.example.repository.utils.ErrorCallback
import com.example.repository.utils.ErrorType
import com.example.repository.utils.SafeApiCall
import com.example.repository.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel(), ErrorCallback {

    private val productsLV = MutableLiveData<State>(State.idle)
    val otherErrorLV = MutableLiveData<ErrorType>()
    val networkErrorLV = MutableLiveData<MessageModel>()

    private fun fetchProductsFromServer() {
        viewModelScope.launch {
            SafeApiCall.safeApiCall(this@MainMenuViewModel) {

                productRepository.getProducts()
                    .flowOn(Dispatchers.IO)
                    .catch { _e ->
                        Log.i("LOG1", "fetchProductsFromServer: ${_e.message}")
                        onError(ErrorType.NETWORK)
                    }
                    .collect {
                        val data = it.data
                        if (data != null && it.errors == null) {
                            if (it.data!!.productSearch.products.isEmpty()) {
                                productsLV.postValue(State.emptyList)
                            } else {
                                val products = ProductDtoMapper.mapFromDtoToEntity(it)
                                productsLV.postValue(State.success(products))
                            }
                        } else {
                            it.errors?.let {
                                productsLV.postValue(State.failed(it))
                            }
                        }
                    }
            }
        }
    }

    private fun fetchProductsFromCache() {
        viewModelScope.launch {
            productRepository.getProductsFromDatabase().flowOn(Dispatchers.IO)
                .catch { _e ->
                    Log.i("LOG1", "fetchProductsFromCache: ${_e.message}")
                    onError(ErrorType.CACHE)
                }
                .collect {
                    val data = it
                    if (data != null) {
                        val products = ProductDaoMapper.mapFromDaoToEntityList(it)
                        productsLV.postValue(State.success(products))
                    } else {
                        productsLV.postValue(State.emptyList)
                    }
                }
        }
    }

    fun observeProducts(): LiveData<State> {
        viewModelScope.launch {
            val cache = async { fetchProductsFromCache() }
            val server = async { fetchProductsFromServer() }

            cache.await()
            server.await()
        }
        return productsLV
    }

    override fun onError(body: MessageModel) {
        networkErrorLV.postValue(body)
    }

    override fun onError(errorType: ErrorType) {
        otherErrorLV.postValue(errorType)
    }
}