package com.br.ambev.exercise.product.database.dao

import androidx.room.*
import com.br.ambev.exercise.product.database.entities.ProductEntities

@Dao
interface ProductDao {

    @get:Query("Select * From product")
    val getall: List<ProductEntities>

    @Insert
    fun insert(productEntities: ProductEntities)

    @Insert
    fun insertAll(productEntities:List<ProductEntities>)

    @Update
    fun update(productEntities: ProductEntities)

    @Delete
    fun delete(productEntities: ProductEntities)
}
