package com.fanreact.shoppingcartpricecalculator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fanreact.shoppingcartpricecalculator.product.Product
import com.fanreact.shoppingcartpricecalculator.product.ProductCategory

class CreateProductViewModel : ViewModel() {
    private val createdProductMutableLiveData = MutableLiveData<Product?>()
    val createdProductLiveData: LiveData<Product?> get() = createdProductMutableLiveData

    private var cost: Double = -1.0
    private var productName: String = ""
    private var isImported: Boolean = false
    private var category: ProductCategory? = null

    fun setCategory(category: ProductCategory) {
        this.category = category
    }

    fun setCost(costEntry: String) {
        cost = costEntry.toDouble()
    }

    fun setName(name: String) {
        this.productName = name
    }

    fun setIsImported(isImported: Boolean) {
        this.isImported = isImported
    }

    fun submitProduct() {
        if (!productName.isEmpty() && category != null && cost > 0) {
            val product = Product(productName, cost, category!!, isImported)
            createdProductMutableLiveData.postValue(product)
        } else {
            createdProductMutableLiveData.postValue(null)
        }
    }
}