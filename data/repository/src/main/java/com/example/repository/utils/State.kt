package com.example.repository.utils

import com.example.entity.models.ErrorModel

sealed class State {

    data class success<T> (val data:T) : State()
    data class failed(val message:String?) : State()
    object emptyList : State()
    object loading : State()
    object idle : State()
}