package com.fanreact.shoppingcartpricecalculator.tax

import org.junit.Assert
import org.junit.Test

class ImportTaxTest {
    @Test
    fun taxAmount() {
        val importTax = ImportTax()

        val baseCost = 1.0

        val expectedValue = 0.05
        val calculatedValue = importTax.taxAmount(baseCost)

        Assert.assertEquals(
            "Import tax amount calculation failed",
            expectedValue,
            calculatedValue,
            0.01
        )
    }

    @Test
    fun totalPrice() {
        val importTax = ImportTax()

        val baseCost = 15001.25

        val expectedValue = 15751.35

        val calculatedValue = importTax.totalPriceWithTaxes(baseCost)

        Assert.assertEquals(
            "Import tax total price calculation failed",
            expectedValue,
            calculatedValue,
            0.01
        )
    }
}