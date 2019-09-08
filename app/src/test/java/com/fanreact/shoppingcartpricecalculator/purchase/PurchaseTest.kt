package com.fanreact.shoppingcartpricecalculator.purchase

import com.fanreact.shoppingcartpricecalculator.product.Product
import com.fanreact.shoppingcartpricecalculator.product.ProductCategory
import org.junit.Test

import org.junit.Assert.*

class PurchaseTest {

    @Test
    fun addProduct() {
        val purchase = Purchase()

        val product = Product("test", 10.0, ProductCategory.AlcoholicBeverage, false)

        purchase.addProduct(product)
        assert(purchase.products().count() == 1) {"Product not added"}
        assert(purchase.products()[0].id == product.id) {"Added product ID does not match"}
    }

    @Test
    fun addProducts() {
        val purchase = Purchase()

        val product1 = Product("test", 10.0, ProductCategory.AlcoholicBeverage, false)
        val product2 = Product("test1", 200.0, ProductCategory.ConsumerElectronics, true)

        purchase.addProducts(listOf(product1, product2))
        assert(purchase.products().count() == 2) {"Products not added"}
        assert(purchase.products()[0].id == product1.id && purchase.products()[1].id == product2.id) {"Added product IDs do not match"}
    }

    @Test
    fun removeProduct() {
        val purchase = Purchase()

        val product1 = Product("test", 10.0, ProductCategory.AlcoholicBeverage, false)
        val product2 = Product("test1", 200.0, ProductCategory.ConsumerElectronics, true)

        purchase.addProducts(listOf(product1, product2))

        purchase.removeProduct(product1)
        assert(purchase.products().count() == 1) {"Product not removed correctly"}
        assert(purchase.products()[0].id == product2.id) {"Wrong product was removed"}
    }

    @Test
    fun clearProducts() {
        val purchase = Purchase()

        purchase.addProducts(listOf(Product("test", 10.0, ProductCategory.AlcoholicBeverage, false)))

        purchase.clearProducts()
        assert(purchase.products().count() == 0) {"Products were not cleared"}
    }

    @Test
    fun getTotalSalesTax() {
        val purchase = Purchase()

        val products = listOf(
            Product("imported coffee", 11.0, ProductCategory.Coffee, true),
            Product("imported scooter", 15001.25, ProductCategory.Vehicle, true)
        )
        purchase.addProducts(products)

        assert(purchase.totalSalesTax == 2250.8) {"Total sales tax not calculated correctly"}
    }

    @Test
    fun getTotalPriceWithTax() {
        val purchase = Purchase()

        val products = listOf(
            Product("imported coffee", 11.0, ProductCategory.Coffee, true),
            Product("imported scooter", 15001.25, ProductCategory.Vehicle, true)
        )
        purchase.addProducts(products)

        assert(purchase.totalPriceWithTax == 17263.05) {"Total sales tax not calculated correctly"}
    }
}