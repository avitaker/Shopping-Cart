package com.fanreact.shoppingcartpricecalculator.utilities

import org.junit.Test

import org.junit.Assert.*

class DataConversionUtilsTest {
    @Test
    fun priceDisplayString() {
        assert(DataConversionUtils.priceDisplayString(43.25) == "43.25")
        assert(DataConversionUtils.priceDisplayString(230.3) == "230.30")
    }
}