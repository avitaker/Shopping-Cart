package com.fanreact.shoppingcartpricecalculator.utilities

object DataConversionUtils {
    fun priceDisplayString(priceAsDouble: Double) : String {
        return String.format("%.2f", priceAsDouble)
    }
}