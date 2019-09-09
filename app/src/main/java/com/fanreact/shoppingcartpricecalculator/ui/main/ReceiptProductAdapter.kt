package com.fanreact.shoppingcartpricecalculator.ui.main

import android.content.Context
import com.fanreact.shoppingcartpricecalculator.utilities.DataConversionUtils

class ReceiptProductAdapter(context: Context): BaseProductAdapter(context) {
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.tvCost.text = DataConversionUtils.priceDisplayString(products[position].totalPriceWithTax())
    }
}