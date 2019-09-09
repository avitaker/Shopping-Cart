package com.fanreact.shoppingcartpricecalculator.product

import com.fanreact.shoppingcartpricecalculator.tax.ImportTax
import com.fanreact.shoppingcartpricecalculator.tax.SalesTax
import com.fanreact.shoppingcartpricecalculator.utilities.BaseDatum
import java.util.*

class Product(var name: String, var baseCost: Double, val productCategory: ProductCategory, val isImported: Boolean) : BaseDatum() {

    private val salesTax = SalesTax(productCategory)
    private val importTax = ImportTax()

    fun totalPriceWithTax() : Double {
        if (isImported) {
            return baseCost + salesTax.taxAmount(baseCost) + importTax.taxAmount(baseCost)
        } else {
            return salesTax.totalPriceWithTaxes(baseCost)
        }
    }

    fun totalTax() : Double {
        if (isImported) {
            return salesTax.taxAmount(baseCost) + importTax.taxAmount(baseCost)
        } else {
            return salesTax.taxAmount(baseCost)
        }
    }

    override fun equals(other: Any?): Boolean {
        other?.let {
            if (it is Product) {
                return this.name.toLowerCase(Locale.getDefault()) == it.name.toLowerCase(Locale.getDefault())
                        && this.baseCost == it.baseCost
                        && this.productCategory == it.productCategory
                        && this.isImported == it.isImported
            } else {
                return super.equals(other)
            }
        } ?: run {
            return super.equals(other)
        }
    }
}