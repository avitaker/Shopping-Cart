package com.fanreact.shoppingcartpricecalculator.tax

open class BaseTax(val taxPercentage: Double) {
    fun taxAmount(baseCost: Double) = roundToNearest5Cents(
        calculateTaxAmount(baseCost, taxPercentage)
    )

    fun totalPriceWithTaxes(baseCost: Double) = baseCost + taxAmount(baseCost)

    private fun calculateTaxAmount(baseCost: Double, taxPercentage: Double) : Double {
        return baseCost * (taxPercentage / 100.toDouble())
    }

    private fun roundToNearest5Cents(number: Double) : Double {
        val df = java.text.DecimalFormat("#.##")
        df.roundingMode = java.math.RoundingMode.DOWN
        fun roundDownDecimal(decimal: Double): Double {
            return df.format(decimal).toDouble()
        }

        val difference = number % 0.05

        val amountToAdd = if (roundDownDecimal(difference) != 0.0) {
            0.05 - difference
        } else {
            0.0
        }
        return roundDownDecimal(number + amountToAdd)
    }
}