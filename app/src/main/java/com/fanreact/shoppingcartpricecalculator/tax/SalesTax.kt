package com.fanreact.shoppingcartpricecalculator.tax

import com.fanreact.shoppingcartpricecalculator.product.ProductCategory
import com.fanreact.shoppingcartpricecalculator.product.salesTaxPercentage

class SalesTax(productCategory: ProductCategory) : BaseTax(taxPercentage = productCategory.salesTaxPercentage())