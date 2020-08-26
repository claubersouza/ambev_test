package com.br.ambev.exercise.network

import com.br.ambev.exercise.product.model.ItemsProductsModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApiRequester @Inject constructor(private val apiService: ApiService) {
    fun getProducts(): Single<List<ItemsProductsModel>> = apiService.getProducts().subscribeOn(Schedulers.io())
}
