package com.fanreact.shoppingcartpricecalculator.purchase

import com.fanreact.shoppingcartpricecalculator.product.Product
import com.fanreact.shoppingcartpricecalculator.utilities.BaseDatum
import java.util.*

class Purchase(initialListOfProducts: List<ProductCounter> = listOf()) : BaseDatum() {
    private val listOfProducts = mutableListOf<ProductCounter>().apply {
        addAll(initialListOfProducts)
    }
    private var purchaseCompletedDate: Date? = null

    fun products() = listOfProducts

    var totalSalesTax = 0.0
    var totalPriceWithTax = 0.0

    fun addProduct(product: ProductCounter, updateTotals: Boolean = true) {
        listOfProducts.firstOrNull { it.product.id == product.product.id || it.product.equals(product.product) }?.let {
            listOfProducts[listOfProducts.indexOf(it)].quantiy = product.quantiy + it.quantiy
        } ?: run {
            listOfProducts.add(product)
        }

        if (updateTotals) {
            calculateReceiptTotals()
        }
    }

    fun addProducts(products: List<ProductCounter>) {
        products.forEach { addProduct(it, false) }
        calculateReceiptTotals()
    }

    fun removeProduct(product: ProductCounter) {
        listOfProducts.firstOrNull { it.product.id == product.product.id }?.let {
            listOfProducts.remove(it)
        }
        calculateReceiptTotals()
    }

    fun clearProducts() {
        this.listOfProducts.clear()
        calculateReceiptTotals()
    }

    fun completePurchase() {
        purchaseCompletedDate = Date()
        calculateReceiptTotals()
    }

    fun unCompletePurchase() {
        purchaseCompletedDate = null
        calculateReceiptTotals()
    }

    fun isComplete() = purchaseCompletedDate != null

    private fun calculateReceiptTotals() {
        totalSalesTax()
        totalPriceWithTax()
    }

    private fun totalSalesTax() : Double {
        totalSalesTax = 0.0
        listOfProducts.forEach { product ->
            totalSalesTax += product.product.totalTax() * product.quantiy
        }

        return totalSalesTax
    }

    private fun totalPriceWithTax() : Double {
        totalPriceWithTax = 0.0
        listOfProducts.forEach { product ->
            totalPriceWithTax += product.product.totalPriceWithTax() * product.quantiy
        }

        return totalPriceWithTax
    }
}