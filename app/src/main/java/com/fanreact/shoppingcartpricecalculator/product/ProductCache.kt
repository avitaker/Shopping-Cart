package com.fanreact.shoppingcartpricecalculator.product

import com.fanreact.shoppingcartpricecalculator.utilities.BaseCache
import java.util.*

object ProductCache : BaseCache<Product>() {
    override fun put(product: Product) {
        values().firstOrNull { pr -> pr.name.toLowerCase(Locale.getDefault()) == product.name.toLowerCase(Locale.getDefault()) }?.let {
            remove(it.id)
        }
        super.put(product)
    }
}