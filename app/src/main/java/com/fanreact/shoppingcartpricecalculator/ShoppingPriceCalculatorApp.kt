package com.fanreact.shoppingcartpricecalculator

import android.app.Application
import com.fanreact.shoppingcartpricecalculator.purchase.PurchaseCache
import com.fanreact.shoppingcartpricecalculator.purchase.PurchaseSeeder

class ShoppingPriceCalculatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PurchaseSeeder.Purchases.forEach {
            PurchaseCache.put(it)
        }
    }
}