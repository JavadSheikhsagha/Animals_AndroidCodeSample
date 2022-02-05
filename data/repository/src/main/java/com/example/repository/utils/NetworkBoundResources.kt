package com.example.presentation.products

import com.example.entity.models.MessageModel
import com.example.repository.utils.ErrorCallback
import com.example.repository.utils.ErrorType
import com.example.repository.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    emitter:ErrorCallback?
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(State.loading(data,null))

        try {
            withContext(Dispatchers.IO) {
                saveFetchResult(fetch())
                query().map { State.success(it) }
            }
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val message = MessageModel(
                        listOf(e.message()),
                        e.code()
                    )
                    emitter?.onError(message)
                    query().map { State.failed(it,e) }
                }
                is IOException -> {
                    emitter?.onError(ErrorType.NETWORK)
                    query().map { State.failed(it,e) }
                }
                is SocketTimeoutException -> {
                    emitter?.onError(ErrorType.TIMEOUT)
                    query().map { State.failed(it,e) }
                }
                else -> {
                    emitter?.onError(ErrorType.UNKNOWN)
                    query().map { State.failed(it,e) }
                }
            }
            null
        }

//        try {
//            saveFetchResult(fetch())
//            query().map { State.success(it) }
//        } catch (throwable: Throwable) {
//            query().map { State.failed(it,throwable) }
//        }
    } else {
        query().map { State.success(it) }
    }

    emitAll(flow)
}