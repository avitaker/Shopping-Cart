package com.fanreact.shoppingcartpricecalculator.purchase

import com.fanreact.shoppingcartpricecalculator.product.ProductCache
import com.fanreact.shoppingcartpricecalculator.utilities.BaseCache

object PurchaseCache : BaseCache<Purchase>() {
    override fun put(purchase: Purchase) {
        val previousPurchase = get(purchase.id)
        if (previousPurchase != null && previousPurchase.products().sortedBy { p -> p.product.id } != purchase.products().sortedBy { p -> p.product.id }) {
            val newPurchase = Purchase(purchase.products()).apply {
                if (purchase.isComplete()) {
                    completePurchase()
                }
            }
            super.put(newPurchase)
        } else {
            super.put(purchase)
        }
        purchase.products().forEach {
            ProductCache.put(it.product)
        }
    }
}