package com.example.entity.models

data class ErrorModel(
    val messages:MessageMainModel,
    val category:String
)

data class MessageMainModel(
    val message: List<String>
)
