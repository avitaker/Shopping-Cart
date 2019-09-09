package com.fanreact.shoppingcartpricecalculator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.fanreact.shoppingcartpricecalculator.product.Product
import com.fanreact.shoppingcartpricecalculator.purchase.Purchase
import com.fanreact.shoppingcartpricecalculator.purchase.PurchaseCache
import com.fanreact.shoppingcartpricecalculator.utilities.DataConversionUtils

class MainViewModel : ViewModel() {
    private var purchase : Purchase = Purchase()
    set(value) {
        field = value
        purchaseMutableLiveData.postValue(value)
    }

    private val purchaseMutableLiveData = MutableLiveData<Purchase>()

    private val completedPurchaseMutableLiveData = MutableLiveData<Purchase>()
    val completedPurchaseLiveData: LiveData<Purchase> get() = completedPurchaseMutableLiveData

    val productsLiveData = MediatorLiveData<List<Product>>()
    val salesTaxLiveData = MediatorLiveData<String>()
    val totalPriceLiveData = MediatorLiveData<String>()

    init {
        productsLiveData.addSource(purchaseMutableLiveData) { purchase ->
            productsLiveData.postValue(purchase.products())
        }
        salesTaxLiveData.addSource(purchaseMutableLiveData) { purchase ->
            salesTaxLiveData.postValue(DataConversionUtils.priceDisplayString(purchase.totalSalesTax))
        }
        totalPriceLiveData.addSource(purchaseMutableLiveData) { purchase ->
            salesTaxLiveData.postValue(DataConversionUtils.priceDisplayString(purchase.totalPriceWithTax))
        }
    }

    fun seedPurchase(purchase: Purchase) {
        this.purchase = purchase
    }

    fun addProducts(listOfProducts: List<Product>) {
        purchase = purchase.apply {
            addProducts(listOfProducts)
        }
    }

    fun addProduct(product: Product) {
        purchase = purchase.apply {
            addProduct(product)
        }
    }

    fun removeProduct(product: Product) {
        purchase = purchase.apply {
            removeProduct(product)
        }
    }

    fun clearProducts() {
        purchase = purchase.apply {
            clearProducts()
        }
    }

    fun completePurchase() {
        completedPurchaseMutableLiveData.postValue(purchase)
        PurchaseCache.put(purchase)
    }
}
