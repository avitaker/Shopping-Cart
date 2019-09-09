package com.fanreact.shoppingcartpricecalculator.purchase

import androidx.test.runner.AndroidJUnit4
import com.fanreact.shoppingcartpricecalculator.product.Product
import com.fanreact.shoppingcartpricecalculator.product.ProductCategory
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PurchaseCacheInstrumentedTest {
    @Test
    fun cacheWalkthrough() {
        PurchaseCache.clear()

        val product1 = ProductCounter(Product("test", 10.0, ProductCategory.Coffee, true), 1)

        val purchase1 = Purchase(listOf(product1))


        PurchaseCache.put(purchase1)

        val cachedPurchase = PurchaseCache.get(purchase1.id)

        assert(cachedPurchase.id == purchase1.id) { "Inserted purchase does not have same ID" }

        assert(cachedPurchase.products()[0].product.id == product1.product.id) { "Inserted purchase does not have the same product" }


        val product2 = ProductCounter(Product("test", 10.0, ProductCategory.Vehicle, true), 1)
        val purchase2 = Purchase(listOf(product1, product2))

        PurchaseCache.put(purchase2)

        assert(PurchaseCache.values().count() == 2) { "second purchase not added" }

        PurchaseCache.remove(purchase1.id)

        assert(PurchaseCache.values().count() == 1) { "purchase not removed" }

        assert(PurchaseCache.values()[0].id == purchase2.id) {"wrong purchase removed"}

        PurchaseCache.clear()
    }
}