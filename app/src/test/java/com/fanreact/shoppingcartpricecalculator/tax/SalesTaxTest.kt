package com.fanreact.shoppingcartpricecalculator.tax

import com.fanreact.shoppingcartpricecalculator.product.ProductCategory
import com.fanreact.shoppingcartpricecalculator.tax.ImportTax
import com.fanreact.shoppingcartpricecalculator.tax.SalesTax
import org.junit.Assert.assertEquals
import org.junit.Test

class SalesTaxTest {
    @Test
    fun taxAmountTaxedProducts() {
        val baseCost = 55.00

        val expectedValue = 5.5

        listOf(ProductCategory.Vehicle, ProductCategory.AlcoholicBeverage, ProductCategory.ConsumerElectronics).forEach { taxedCategory ->
            val salesTax = SalesTax(taxedCategory)
            val calculatedValue = salesTax.taxAmount(baseCost)

            assertEquals("Sales tax amount calculation failed for TAXED products", expectedValue, calculatedValue, 0.01)
        }
    }


    @Test
    fun taxAmountExemptProducts() {
        val baseCost = 0.99

        val expectedValue = 0.0

        listOf(ProductCategory.Candy, ProductCategory.Popcorn, ProductCategory.Coffee).forEach { exemptCategory ->
            val salesTax = SalesTax(exemptCategory)
            val calculatedValue = salesTax.taxAmount(baseCost)
            assertEquals("Sales tax amount calculation failed for EXEMPT products", expectedValue, calculatedValue, 0.01)
        }
    }

    @Test
    fun totalPriceTaxedProducts() {
        val baseCost = 99.99

        val expectedValue = 109.99

        listOf(ProductCategory.Vehicle, ProductCategory.AlcoholicBeverage, ProductCategory.ConsumerElectronics).forEach { taxedCategory ->
            val salesTax = SalesTax(taxedCategory)
            val calculatedValue = salesTax.totalPriceWithTaxes(baseCost)

            assertEquals("Total with sales tax calculation failed for TAXED products", expectedValue, calculatedValue, 0.01)
        }
    }

    @Test
    fun totalPriceExemptProducts() {
        val baseCost = 16.00

        val expectedValue = 16.00

        listOf(ProductCategory.Candy, ProductCategory.Popcorn, ProductCategory.Coffee).forEach { exemptCategory ->
            val salesTax = SalesTax(exemptCategory)
            val calculatedValue = salesTax.totalPriceWithTaxes(baseCost)

            assertEquals("Total with sales tax calculation failed for EXEMPT products", expectedValue, calculatedValue, 0.01)
        }
    }
}