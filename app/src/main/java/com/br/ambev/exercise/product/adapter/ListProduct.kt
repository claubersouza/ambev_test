package com.br.ambev.exercise.product.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.br.ambev.exercise.R
import com.br.ambev.exercise.product.database.AppDatabase
import com.br.ambev.exercise.product.database.entities.ProductEntities
import com.br.ambev.exercise.product.database.repository.ProductRepository
import com.br.ambev.exercise.utils.Utils
import com.squareup.picasso.Picasso

class ListProduct(private val context: Context?, private val dataSource: List<ProductEntities>?):BaseAdapter() {

    private lateinit var repository: ProductRepository
    private var utils: Utils? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val viewHolder:ViewHolder
        var convertView = convertView

        if (convertView == null) {
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.list_products, parent,false)

            viewHolder = ViewHolder(convertView)
            utils = Utils()
            convertView.tag = viewHolder
        }
        else {
            viewHolder = convertView.tag as ViewHolder
        }

        viewHolder.edtAmount?.setText(dataSource?.get(position)?.amountProduct.toString())
        viewHolder.txtTitle?.text = dataSource?.get(position)?.nameProduct
        viewHolder.txtDescription?.text = dataSource?.get(position)?.descriptionProduct
        viewHolder.txtPrice?.text = dataSource?.get(position)?.priceProduct.toString()
        viewHolder.txtPriceOld?.text = dataSource?.get(position)?.priceOldProduct.toString()

        Picasso.get()
            .load(dataSource?.get(position)?.imageProduct)
            .error(R.drawable.image_teste)
            .into(viewHolder.imagePhoto)


        if (viewHolder.txtPriceOld?.text.toString().toDouble() > viewHolder.txtPrice?.text.toString().toDouble())
            viewHolder.txtPriceOld?.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        viewHolder.btnAdd?.let {
            if (dataSource?.get(position)?.purchasedProduct!!)
                it.text = "OK"
            else
                it.text = "ADD"
        }

        viewHolder.btnAdd?.setOnClickListener {
            dataSource?.get(position)?.purchasedProduct = !this.dataSource?.get(position)?.purchasedProduct!!
            dataSource?.get(position)?.let { it1 -> updateProduct(it1) }

            if (dataSource?.get(position)?.purchasedProduct!!) {
                viewHolder.btnAdd?.text = "OK"
            }
            else
                viewHolder.btnAdd?.text = "ADD"
        }


        viewHolder.btnPlus?.setOnClickListener {
            var value = viewHolder.edtAmount?.text.toString().toInt()
            value += 1
            viewHolder.edtAmount?.setText(value.toString())
            dataSource?.get(position)?.amountProduct = value
            dataSource?.get(position)?.let { it1 -> updateProduct(it1) }
        }

        viewHolder.btnDecrease?.setOnClickListener {
            var value = viewHolder.edtAmount?.text.toString().toInt()
            if (value > 0)
                value -= 1
            viewHolder.edtAmount?.setText(value.toString())
            dataSource?.get(position)?.amountProduct = value
            dataSource?.get(position)?.let { it1 -> updateProduct(it1) }
        }
        return convertView
    }

    private fun updateProduct(product: ProductEntities) {
        val productDao = this.context?.let {
            AppDatabase.getDatabase(it).productDao()

        }
        repository = productDao?.let {
            ProductRepository(it)
        }!!

        repository.setUpdateProduct(product)
    }



    override fun getItem(position: Int): ProductEntities? {
        return dataSource?.let {
            it[position]
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource?.size!!
    }

    private class ViewHolder(view:View){
        var txtDescription: TextView? = view.findViewById(R.id.ambev_product_item_description)
        var txtTitle: TextView? = view.findViewById(R.id.ambev_product_item_title)
        var txtPrice: TextView? = view.findViewById(R.id.ambev_product_item_price)
        var txtPriceOld: TextView? = view.findViewById(R.id.ambev_product_item_price_old)
        var btnPlus: Button? = view.findViewById(R.id.ambev_product_item_plus)
        var btnDecrease: Button? = view.findViewById(R.id.ambev_product_item_decrease)
        var btnAdd: Button? = view.findViewById(R.id.ambev_product_item_add)
        var edtAmount: EditText? = view.findViewById(R.id.ambev_product_item_amount_edit)
        var imagePhoto:ImageView = view.findViewById(R.id.ambev_product_item_image)
    }
}


