package com.fanreact.shoppingcartpricecalculator.purchase

import com.fanreact.shoppingcartpricecalculator.product.Product
import com.fanreact.shoppingcartpricecalculator.product.ProductCategory
import org.junit.Test

class PurchaseTest {

    @Test
    fun addProduct() {
        val purchase = Purchase()

        val product = ProductCounter(Product("test", 10.0, ProductCategory.AlcoholicBeverage, false), 1)

        purchase.addProduct(product)
        assert(purchase.products().count() == 1) {"Product not added"}
        assert(purchase.products()[0].product.id == product.product.id) {"Added product ID does not match"}
    }

    @Test
    fun addProducts() {
        val purchase = Purchase()

        val product1 = ProductCounter(Product("test", 10.0, ProductCategory.AlcoholicBeverage, false), 1)
        val product2 = ProductCounter(Product("test1", 200.0, ProductCategory.ConsumerElectronics, true), 1)

        purchase.addProducts(listOf(product1, product2))
        assert(purchase.products().count() == 2) {"Products not added"}
        assert(purchase.products()[0].product.id == product1.product.id && purchase.products()[1].product.id == product2.product.id) {"Added product IDs do not match"}
    }

    @Test
    fun removeProduct() {
        val purchase = Purchase()

        val product1 = ProductCounter(Product("test", 10.0, ProductCategory.AlcoholicBeverage, false), 1)
        val product2 = ProductCounter(Product("test1", 200.0, ProductCategory.ConsumerElectronics, true), 1)

        purchase.addProducts(listOf(product1, product2))

        purchase.removeProduct(product1)
        assert(purchase.products().count() == 1) {"Product not removed correctly"}
        assert(purchase.products()[0].product.id == product2.product.id) {"Wrong product was removed"}
    }

    @Test
    fun clearProducts() {
        val purchase = Purchase()

        purchase.addProducts(listOf(ProductCounter(Product("test", 10.0, ProductCategory.AlcoholicBeverage, false), 1)))

        purchase.clearProducts()
        assert(purchase.products().count() == 0) {"Products were not cleared"}
    }

    @Test
    fun getTotalSalesTax() {
        val purchase = Purchase()

        val products = listOf(
            ProductCounter(Product("imported coffee", 11.0, ProductCategory.Coffee, true), 1),
            ProductCounter(Product("imported scooter", 15001.25, ProductCategory.Vehicle, true), 1)
        )
        purchase.addProducts(products)

        assert(purchase.totalSalesTax == 2250.8) {"Total sales tax not calculated correctly"}
    }

    @Test
    fun getTotalPriceWithTax() {
        val purchase = Purchase()

        val products = listOf(
            ProductCounter(Product("imported coffee", 11.0, ProductCategory.Coffee, true), 1),
            ProductCounter(Product("imported scooter", 15001.25, ProductCategory.Vehicle, true), 1)
        )
        purchase.addProducts(products)

        assert(purchase.totalPriceWithTax == 17263.05) {"Total sales tax not calculated correctly"}
    }

    @Test
    fun idUniqueness() {
        val previousIds = mutableListOf<String>()
        for (i in 0 until 1000) {
            val purchase = Purchase()

            assert(previousIds.firstOrNull { id -> id == purchase.id } == null) {
                "ID match found on try $i"
            }

            previousIds.add(purchase.id)
        }
    }
}