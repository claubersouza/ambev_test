package com.br.ambev.exercise.network

import com.br.ambev.exercise.Constants
import com.br.ambev.exercise.product.model.ItemsProductsModel
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.URLS.URL_ENDPOINT)
    fun getProducts():Single<List<ItemsProductsModel>>
}
