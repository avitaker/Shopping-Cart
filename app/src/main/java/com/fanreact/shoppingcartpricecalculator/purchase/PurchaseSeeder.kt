package com.fanreact.shoppingcartpricecalculator.purchase

import com.fanreact.shoppingcartpricecalculator.product.Product
import com.fanreact.shoppingcartpricecalculator.product.ProductCategory

object PurchaseSeeder {
    val Purchase1 = Purchase(listOf(
        Product("16lb bag of Skittles", 16.00, ProductCategory.Candy, false),
        Product("Walkman", 99.99, ProductCategory.ConsumerElectronics, false),
        Product("Microwave Popcorn", 0.99, ProductCategory.Popcorn, false)
    ))

    val Purchase2 = Purchase(listOf(
        Product("Bag of Vanilla-Hazelnut Coffee", 11.00, ProductCategory.Coffee, true),
        Product("Vespa", 15001.25, ProductCategory.Vehicle, true)
    ))

    val Purchase3 = Purchase(listOf(
        Product("Crate of almond snickers", 75.99, ProductCategory.Candy, true),
        Product("Discman", 55.00, ProductCategory.ConsumerElectronics, false),
        Product("Bottle Of Wine", 10.00, ProductCategory.AlcoholicBeverage, true),
        Product("300# bag of Fair-Trade Coffee", 997.99, ProductCategory.Coffee, false)
    ))

    val Purchases = listOf(Purchase1, Purchase2, Purchase3)
}