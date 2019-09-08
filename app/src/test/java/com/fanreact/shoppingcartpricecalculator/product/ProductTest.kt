package com.fanreact.shoppingcartpricecalculator.product

import org.junit.Test

import org.junit.Assert.*

class ProductTest {
    val exemptAndUnimportedProduct = Product("test", 16.00, ProductCategory.Candy, false)
    val exemptAndImportedProduct = Product("test2", 11.00, ProductCategory.Candy, true)
    val nonExemptAndUnimportedProduct = Product("test3", 99.99, ProductCategory.ConsumerElectronics, false)
    val nonExemptAndImportedProduct = Product("test4", 15001.25, ProductCategory.AlcoholicBeverage, true)

    @Test
    fun totalPriceWithTax() {

        assertEquals("EXEMPT and UNIMPORTED product total price calculation failed", 16.00, exemptAndUnimportedProduct.totalPriceWithTax(), 0.01)


        assertEquals("EXEMPT and IMPORTED product total price calculation failed", 11.55, exemptAndImportedProduct.totalPriceWithTax(), 0.01)


        assertEquals("NONEXEMPT and UNIMPORTED product total price calculation failed", 109.99, nonExemptAndUnimportedProduct.totalPriceWithTax(), 0.01)


        assertEquals("NONEXEMPT and IMPORTED product total price calculation failed", 17251.5, nonExemptAndImportedProduct.totalPriceWithTax(), 0.01)
    }

    @Test
    fun totalTax() {
        assertEquals("EXEMPT and UNIMPORTED product tax calculation failed", 0.0, exemptAndUnimportedProduct.totalTax(), 0.01)

        assertEquals("EXEMPT and IMPORTED product tax calculation failed", 0.55, exemptAndImportedProduct.totalTax(), 0.01)

        assertEquals("NONEXEMPT and UNIMPORTED tax price calculation failed", 10.0, nonExemptAndUnimportedProduct.totalTax(), 0.01)

        assertEquals("NONEXEMPT and IMPORTED tax price calculation failed", 2250.25, nonExemptAndImportedProduct.totalTax(), 0.01)
    }

    @Test
    fun idUniqueness() {
        assertNotEquals("ID duplicate", exemptAndUnimportedProduct.id, exemptAndImportedProduct.id)
        assertNotEquals("ID duplicate", exemptAndImportedProduct.id, nonExemptAndUnimportedProduct.id)
        assertNotEquals("ID duplicate", nonExemptAndUnimportedProduct.id, nonExemptAndImportedProduct.id)
        assertNotEquals("ID duplicate", nonExemptAndImportedProduct.id, exemptAndUnimportedProduct.id)
    }
}