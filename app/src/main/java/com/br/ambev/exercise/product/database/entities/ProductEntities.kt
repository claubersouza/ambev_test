package com.br.ambev.exercise.product.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntities(
    @PrimaryKey
    val id: Int?,
    val nameProduct: String,
    var amountProduct: Int,
    val descriptionProduct: String?,
    val priceProduct: Double,
    val priceOldProduct: Double,
    var purchasedProduct: Boolean,
    val recycledProduct: Boolean,
    val imageProduct: String)

