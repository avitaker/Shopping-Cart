package com.fanreact.shoppingcartpricecalculator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fanreact.shoppingcartpricecalculator.product.Product
import com.fanreact.shoppingcartpricecalculator.product.ProductCache
import com.fanreact.shoppingcartpricecalculator.purchase.ProductCounter

class AddProductToCartViewModel : ViewModel() {
    private var productCounter: ProductCounter? = null

    private val finalProductCounterMutableLiveData = MutableLiveData<ProductCounter?>()
    val finalProductCounterLiveData: LiveData<ProductCounter?> get() = finalProductCounterMutableLiveData

    fun displayProductNames() : List<String> {
        val listOfString = mutableListOf<String>()
        ProductCache.values().forEach {
            listOfString.add(it.name)
        }

        return listOfString
    }

    fun displayQuantities() = mutableListOf<String>().apply {
        for (i in 1 until 100) {
            add(i.toString())
        }
    }

    fun setProductWithName(name: String) {
        val product = ProductCache.values().firstOrNull { p -> p.name == name }
        product?.let {
            setProduct(it)
        }
    }

    fun setProduct(product: Product) {
        productCounter = ProductCounter(product)
    }

    fun setQuantity(quantity: Int) {
        productCounter?.quantiy = quantity
    }

    fun addSelectedToCart() {
        if (productCounter != null) {
            finalProductCounterMutableLiveData.postValue(productCounter)
        } else {
            finalProductCounterMutableLiveData.postValue(null)
        }
    }
}