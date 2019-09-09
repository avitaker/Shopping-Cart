package com.fanreact.shoppingcartpricecalculator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fanreact.shoppingcartpricecalculator.purchase.ProductCounter
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

    val productsLiveData = MediatorLiveData<List<ProductCounter>>()
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
        this.purchase = purchase.apply { unCompletePurchase() }
    }

    fun addProducts(listOfProducts: List<ProductCounter>) {
        purchase = purchase.apply {
            addProducts(listOfProducts)
        }
    }

    fun addProduct(product: ProductCounter) {
        purchase = purchase.apply {
            addProduct(product)
        }
    }

    fun removeProduct(product: ProductCounter) {
        purchase = purchase.apply {
            removeProduct(product)
        }

        if (purchase.products().count() == 0) {
            purchase = Purchase()
        }
    }

    fun clearPurchase() {
        purchase = Purchase()
    }

    fun completePurchase() {
        if (purchase.products().count() > 0) {
            purchase.completePurchase()
            completedPurchaseMutableLiveData.postValue(purchase)
            PurchaseCache.put(purchase)
        }
    }
}
