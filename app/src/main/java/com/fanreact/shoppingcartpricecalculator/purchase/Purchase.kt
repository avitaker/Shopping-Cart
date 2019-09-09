package com.fanreact.shoppingcartpricecalculator.purchase

import com.fanreact.shoppingcartpricecalculator.product.Product
import java.util.*

class Purchase(initialListOfProducts: List<Product> = listOf()) {
    private val listOfProducts = mutableListOf<Product>().apply {
        addAll(initialListOfProducts)
    }

    val id = UUID.randomUUID().toString()

    fun products() = listOfProducts

    var totalSalesTax = 0.0
    var totalPriceWithTax = 0.0

    fun addProduct(product: Product, updateTotals: Boolean = true) {
        var alreadyExists = false
        listOfProducts.firstOrNull { it.id == product.id || it.equals(product) }?.let {
            alreadyExists = true
        }

        if (!alreadyExists) {
            listOfProducts.add(product)
            if (updateTotals) {
                calculateReceiptTotals()
            }
        }
    }

    fun addProducts(products: List<Product>) {
        products.forEach { addProduct(it, false) }
        calculateReceiptTotals()
    }

    fun removeProduct(product: Product) {
        listOfProducts.firstOrNull { it.id == product.id }?.let {
            listOfProducts.remove(it)
        }
        calculateReceiptTotals()
    }

    fun clearProducts() {
        this.listOfProducts.clear()
        calculateReceiptTotals()
    }

    private fun calculateReceiptTotals() {
        totalSalesTax()
        totalPriceWithTax()
    }

    private fun totalSalesTax() : Double {
        totalSalesTax = 0.0
        listOfProducts.forEach { product ->
            totalSalesTax += product.totalTax()
        }

        return totalSalesTax
    }

    private fun totalPriceWithTax() : Double {
        totalPriceWithTax = 0.0
        listOfProducts.forEach { product ->
            totalPriceWithTax += product.totalPriceWithTax()
        }

        return totalPriceWithTax
    }
}