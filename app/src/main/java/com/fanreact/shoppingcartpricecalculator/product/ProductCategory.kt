package com.fanreact.shoppingcartpricecalculator.product

import android.content.Context
import com.fanreact.shoppingcartpricecalculator.R

enum class ProductCategory {
    None,
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

fun ProductCategory.displayString(context: Context) : String {
    when (this) {
        ProductCategory.None -> return context.getString(R.string.none)
        ProductCategory.Vehicle -> return context.getString(R.string.vehicle)
        ProductCategory.AlcoholicBeverage -> return context.getString(R.string.alcoholic_beverage)
        ProductCategory.ConsumerElectronics -> return context.getString(R.string.consumer_electronics)
        ProductCategory.Coffee -> return context.getString(R.string.coffee)
        ProductCategory.Candy -> return context.getString(R.string.candy)
        ProductCategory.Popcorn -> return context.getString(R.string.popcorn)
    }
}

fun parseFromDisplayString(context: Context, string: String) : ProductCategory {
    when (string) {
        context.getString(R.string.none) -> return ProductCategory.None
        context.getString(R.string.vehicle) -> return ProductCategory.Vehicle
        context.getString(R.string.alcoholic_beverage) -> return ProductCategory.AlcoholicBeverage
        context.getString(R.string.consumer_electronics) -> return ProductCategory.ConsumerElectronics
        context.getString(R.string.coffee) -> return ProductCategory.Coffee
        context.getString(R.string.candy) -> return ProductCategory.Candy
        context.getString(R.string.popcorn) -> return ProductCategory.Popcorn
        else -> return ProductCategory.None
    }
}