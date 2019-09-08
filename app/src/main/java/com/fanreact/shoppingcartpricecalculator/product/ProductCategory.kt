package com.fanreact.shoppingcartpricecalculator.product

enum class ProductCategory {
    Vehicle,
    AlcoholicBeverage,
    ConsumerElectronics,
    Coffee,
    Candy,
    Popcorn
}

fun ProductCategory.salesTaxPercentage() : Double {
    when (this) {
        ProductCategory.Coffee,
        ProductCategory.Candy,
        ProductCategory.Popcorn -> {
            return 0.0
        }
        else -> {
            return 10.0
        }
    }
}