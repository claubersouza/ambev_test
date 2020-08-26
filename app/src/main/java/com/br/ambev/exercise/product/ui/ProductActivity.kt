package com.br.ambev.exercise.product.ui

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.br.ambev.exercise.R
import com.br.ambev.exercise.base.getAppComponent
import com.br.ambev.exercise.product.adapter.ListProduct
import com.br.ambev.exercise.product.model.ItemsProductsModel
import com.br.ambev.exercise.product.viewmodel.ProductViewModel
import com.br.ambev.exercise.utils.Utils
import com.br.ambev.exercise.viewmodel.ViewModelFactory
import javax.inject.Inject


open class ProductActivity() : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var viewmodel: ProductViewModel
    private var listView: ListView? = null

    private var utils:Utils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        utils = Utils()
        viewmodel = ViewModelProviders.of(this,factory).get(ProductViewModel::class.java)
        viewmodel.setContext(this)
        viewmodel.fetchProduct()
        observeViewModel()
    }

    private fun populateDb(listProduct: List<ItemsProductsModel>?) {
        val products = viewmodel.getAllProduct()
        listProduct?.let {
            if(products.isEmpty()) {
                viewmodel?.let {vm ->
                    utils?.convertListProduct(it)?.let { it1 -> vm.setAllProduct(it1) }
                }
            }
        }
    }

    private fun observeViewModel() {
        viewmodel.getProduct().observe(this, Observer {value ->
            if(value.isNotEmpty()) {
                populateList(value)
            }
        })
    }

    private fun populateList(listProduct: List<ItemsProductsModel>?) {
        if (viewmodel?.getAllProduct().isEmpty())
            populateDb(listProduct)

            val products = viewmodel?.getAllProduct()
            var adapter = ListProduct(this,products)
            listView = findViewById<ListView>(R.id.listview)
            listView?.let {
                it.adapter = adapter
            }
    }
}
