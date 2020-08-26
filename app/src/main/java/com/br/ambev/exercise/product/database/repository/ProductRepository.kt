package com.br.ambev.exercise.product.database.repository

import com.br.ambev.exercise.product.database.dao.ProductDao
import com.br.ambev.exercise.product.database.entities.ProductEntities

class ProductRepository(private val productDao: ProductDao) {
    fun getAllProduct():List<ProductEntities> {
        return productDao.getall
    }

    fun setAllProduct(products :List<ProductEntities>) {
        productDao.insertAll(products)
    }

    fun setUpdateProduct(product: ProductEntities) {
        productDao.update(product)
    }
}
