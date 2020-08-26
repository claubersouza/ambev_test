package com.br.ambev.exercise.utils

import com.br.ambev.exercise.product.database.entities.ProductEntities
import com.br.ambev.exercise.product.model.ItemsProductsModel

class Utils {

    fun convertListProduct(lstProduct:List<ItemsProductsModel>?):List<ProductEntities> {
        var lstProductDb:MutableList<ProductEntities> = arrayListOf()
        var count = 0
        lstProduct?.forEach { item ->
            val product = ProductEntities(
                priceProduct = item.priceProduct!!,
                priceOldProduct = item.priceOldProduct!!,
                nameProduct = item.nameProduct!!,
                descriptionProduct = item.descriptionProduct!!,
                purchasedProduct = item.purchasedProduct!!,
                recycledProduct =  item.purchasedProduct,
                imageProduct = item.imageProduct!!,
                amountProduct = item.amountProduct!!,
                id = count
            )
            lstProductDb.add(product)
            count++
        }
        return  lstProductDb
    }

    fun convertListItemProduct(lstProductItem:List<ProductEntities>?):List<ItemsProductsModel> {
        var lstProduct:MutableList<ItemsProductsModel> = arrayListOf()
        var count = 0
        lstProductItem?.forEach { item ->
            val product = ItemsProductsModel(
                priceProduct = item.priceProduct!!,
                priceOldProduct = item.priceOldProduct!!,
                nameProduct = item.nameProduct!!,
                descriptionProduct = item.descriptionProduct!!,
                purchasedProduct = item.purchasedProduct!!,
                recycledProduct =  item.purchasedProduct,
                imageProduct = item.imageProduct!!,
                amountProduct = item.amountProduct!!,
                idProduct = item.id.toString()
            )
            lstProduct.add(product)
            count++
        }
        return  lstProduct
    }

    fun convertProduct(itemProduct:ItemsProductsModel):ProductEntities? {
        return ProductEntities(
            priceProduct = itemProduct.priceProduct!!,
            priceOldProduct = itemProduct.priceOldProduct!!,
            nameProduct = itemProduct.nameProduct!!,
            descriptionProduct = itemProduct.descriptionProduct!!,
            purchasedProduct = itemProduct.purchasedProduct!!,
            recycledProduct =  itemProduct.purchasedProduct,
            imageProduct = itemProduct.imageProduct!!,
            amountProduct = itemProduct.amountProduct!!,
            id = itemProduct.idProduct.toInt()
        )
    }
}
