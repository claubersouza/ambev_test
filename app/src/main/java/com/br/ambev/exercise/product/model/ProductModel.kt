package com.br.ambev.exercise.product.model
import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "product")
data class ItemsProductsModel (
    @field:Json(name = "id")
    val idProduct:String,
    @field:Json(name ="product_name")
    val nameProduct: String,
    @field:Json(name ="product_amount")
    var amountProduct: Int?,
    @field:Json(name="product_description")
    val descriptionProduct: String?,
    @field:Json(name ="product_price")
    val priceProduct: Double?,
    @field:Json(name="product_price_old")
    val priceOldProduct: Double?,
    @field:Json(name = "product_purchased")
    val purchasedProduct: Boolean?,
    @field:Json(name ="product_recycled")
    val recycledProduct: Boolean?,
    @field:Json(name = "product_image")
    val imageProduct: String?
):Parcelable
