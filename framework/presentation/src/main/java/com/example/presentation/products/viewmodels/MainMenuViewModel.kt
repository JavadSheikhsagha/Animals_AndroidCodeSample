package com.example.presentation.products.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.entity.models.MessageModel
import com.example.repository.product.AnimalRepository
import com.example.repository.utils.ErrorCallback
import com.example.repository.utils.ErrorType
import com.example.repository.utils.SafeApiCall
import com.example.repository.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val repository : AnimalRepository
) : ViewModel() {

    val animalsLD = repository.getAnimals().asLiveData()
    val otherErrorLV = repository.otherErrorLV
    val networkErrorLV = repository.networkErrorLV

}

