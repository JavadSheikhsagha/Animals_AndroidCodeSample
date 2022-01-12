package com.example.entity.models

data class ProductModel(
    val id:Long,
    val title:String,
    val boothTitle:String,
    val price:Long,
    val weight:Long,
    val rating:Double,
    val rateCount:Int,
) {
}