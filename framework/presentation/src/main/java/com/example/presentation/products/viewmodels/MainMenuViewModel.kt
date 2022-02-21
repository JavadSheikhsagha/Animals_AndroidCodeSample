package com.example.presentation.products.viewmodels

import androidx.lifecycle.*
import com.example.repository.animal.AnimalRepository
import com.example.repository.animal.AnimalRepositoryImpl
import com.example.repository.usecases.GetAnimalUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val getAnimalUsecase: GetAnimalUsecase,
    private val repository: AnimalRepositoryImpl
) : ViewModel() {

    val animalsLD = getAnimalUsecase.invoke().asLiveData()
    val otherErrorLV = repository.otherErrorLV
    val networkErrorLV = repository.networkErrorLV

}

