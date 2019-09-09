package com.fanreact.shoppingcartpricecalculator.purchase

import com.fanreact.shoppingcartpricecalculator.product.Product
import com.fanreact.shoppingcartpricecalculator.product.ProductCategory

object PurchaseSeeder {
    val Purchase1 = Purchase(listOf(
        ProductCounter(Product("16lb bag of Skittles", 16.00, ProductCategory.Candy, false), 1),
        ProductCounter(Product("Walkman", 99.99, ProductCategory.ConsumerElectronics, false), 1),
        ProductCounter(Product("Microwave Popcorn", 0.99, ProductCategory.Popcorn, false), 1)
    ))

    val Purchase2 = Purchase(listOf(
        ProductCounter(Product("Bag of Vanilla-Hazelnut Coffee", 11.00, ProductCategory.Coffee, true), 1),
        ProductCounter(Product("Vespa", 15001.25, ProductCategory.Vehicle, true), 1)
    ))

    val Purchase3 = Purchase(listOf(
        ProductCounter(Product("Crate of almond snickers", 75.99, ProductCategory.Candy, true), 1),
        ProductCounter(Product("Discman", 55.00, ProductCategory.ConsumerElectronics, false), 1),
        ProductCounter(Product("Bottle Of Wine", 10.00, ProductCategory.AlcoholicBeverage, true), 1),
        ProductCounter(Product("300# bag of Fair-Trade Coffee", 997.99, ProductCategory.Coffee, false), 1)
    ))

    val Purchases = listOf(Purchase1, Purchase2, Purchase3)
}