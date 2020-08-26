package com.br.ambev.exercise.product.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ambev.exercise.network.ApiRequester
import com.br.ambev.exercise.product.database.AppDatabase
import com.br.ambev.exercise.product.database.entities.ProductEntities
import com.br.ambev.exercise.product.database.repository.ProductRepository
import com.br.ambev.exercise.product.model.ItemsProductsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ProductViewModel @Inject constructor(private val apiRequester: ApiRequester):ViewModel() {
    private val productData = MutableLiveData<List<ItemsProductsModel>>()
    private val compositeDisposable = CompositeDisposable()
    private var context: Context? = null
    private lateinit var repository: ProductRepository

    fun getProduct(): LiveData<List<ItemsProductsModel>> = productData

    fun fetchProduct() {
        compositeDisposable.add(
            apiRequester.getProducts().observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {result -> productData.value = result},
                    {error -> Log.e("Error","teste")}
                )
        )
    }

    fun setContext(context: Context) {
        this.context = context
    }

    fun setAllProduct(product :List<ProductEntities>) {
        val productDao = this.context?.let {
            AppDatabase.getDatabase(it).productDao()

        }
        repository = productDao?.let {
            ProductRepository(it)
        }!!

        repository.setAllProduct(product)
    }

    fun getAllProduct(): List<ProductEntities> {
        val productDao = this.context?.let {
            AppDatabase.getDatabase(it).productDao()

        }
        repository = productDao?.let {
            ProductRepository(it)
        }!!

        return repository.getAllProduct()
    }

    override fun onCleared() {
        if (compositeDisposable.size() > 0) {
            compositeDisposable.clear()
        }
    }
}
