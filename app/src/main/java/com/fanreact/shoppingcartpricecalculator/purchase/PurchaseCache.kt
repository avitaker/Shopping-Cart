package com.fanreact.shoppingcartpricecalculator.purchase

import android.util.LruCache
import com.fanreact.shoppingcartpricecalculator.product.ProductCache
import com.fanreact.shoppingcartpricecalculator.utilities.BaseCache

object PurchaseCache : BaseCache<Purchase>() {
    override fun put(purchase: Purchase) {
        super.put(purchase)
        purchase.products().forEach {
            ProductCache.put(it)
        }
    }
}