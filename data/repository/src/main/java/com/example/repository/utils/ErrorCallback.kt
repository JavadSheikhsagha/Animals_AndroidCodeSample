package com.example.repository.utils

import com.example.entity.models.ErrorModel
import com.example.entity.models.MessageModel

interface ErrorCallback {
    fun onError(body:MessageModel)
    fun onError(errorType: ErrorType)
}