package com.example.presentation.products.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.entity.models.ErrorModel
import com.example.entity.models.MessageMainModel
import com.example.entity.models.MessageModel
import com.example.local.mappers.AnimalDaoMapper
import com.example.network.mappers.AnimalDtoMapper
import com.example.repository.product.AnimalRepository
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
    private val animalRepository: AnimalRepository
) : ViewModel(), ErrorCallback {

    private val animalsLD = MutableLiveData<State>(State.idle)
    val otherErrorLV = MutableLiveData<ErrorType>()
    val networkErrorLV = MutableLiveData<MessageModel>()

    private fun fetchProductsFromServer() {
        viewModelScope.launch {
            SafeApiCall.safeApiCall(this@MainMenuViewModel) {

                animalRepository.getAnimals()
                    .flowOn(Dispatchers.IO)
                    .catch { _e ->
                        Log.i("LOG1", "fetchProductsFromServer: ${_e.message}")
                        onError(ErrorType.NETWORK)
                    }
                    .collect {
                        val data = it
                        if (data != null) {
                            if (it.isEmpty()) {
                                animalsLD.postValue(State.emptyList)
                            } else {
                                animalsLD.postValue(State.success(it))
                            }
                        } else {
                            animalsLD.postValue(State.failed("failed to connect to server.."))
                        }
                    }
            }
        }
    }

    fun observeAnimals(): LiveData<State> {
        fetchProductsFromServer()
        return animalsLD
    }

    override fun onError(body: MessageModel) {
        networkErrorLV.postValue(body)
    }

    override fun onError(errorType: ErrorType) {
        otherErrorLV.postValue(errorType)
    }
}