package com.example.repository.utils

import com.example.entity.models.ErrorModel

sealed class State<T>(
    val data: T?,
    val error: Throwable? = null
) {

    class success<T>(data: T) : State<T>(data, null)
    class failed<T>(data: T? = null, throwable: Throwable) : State<T>(data, throwable)
    class emptyList<T>(data: T? = null, error: Throwable? = null) : State<T>(data, error)
    class loading<T>(data: T? = null, error: Throwable? = null) : State<T>(data, error)
    class idle<T>(data: T? = null, error: Throwable? = null) : State<T>(data, error)
}
//
//sealed class Resource<T>(
//    val data: T?,
//    val error: Throwable? = null
//) {
//    class Success<T>(data: T?) : Resource<T>(data)
//    class Error<T>(error: Throwable?) : Resource<T>(null, error)
//    class Loading<T> : Resource<T>(null, null)
//    class EmptyList<T> : Resource<T>(null, null)
//    class Idle<T> : Resource<T>(null, null)
//}