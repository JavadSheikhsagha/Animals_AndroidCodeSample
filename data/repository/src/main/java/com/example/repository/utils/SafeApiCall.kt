package com.example.repository.utils

import android.util.Log
import com.example.entity.models.ErrorModel
import com.example.entity.models.MessageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class SafeApiCall {

//    companion object {
//        suspend fun <T> safeApiCall(emitter: ErrorCallback?, response: suspend () -> T): T? {
//            return try {
//                withContext(Dispatchers.IO) {
//                    val responseBody = response.invoke()
//                    responseBody
//                }
//            } catch (e: Exception) {
//                when (e) {
//                    is HttpException -> {
//                        val message = MessageModel(
//                            listOf(e.message()),
//                            e.code()
//                        )
//                        emitter?.onError(message)
//                    }
//                    is IOException -> {
//                        emitter?.onError(ErrorType.NETWORK)
//                    }
//                    is SocketTimeoutException -> {
//                        emitter?.onError(ErrorType.TIMEOUT)
//                    }
//                    else -> emitter?.onError(ErrorType.UNKNOWN)
//                }
//                null
//            }
//        }
//    }
}