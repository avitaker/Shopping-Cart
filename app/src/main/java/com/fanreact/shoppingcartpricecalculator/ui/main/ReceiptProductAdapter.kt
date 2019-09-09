package com.fanreact.shoppingcartpricecalculator.ui.main

import android.content.Context
import com.fanreact.shoppingcartpricecalculator.utilities.DataConversionUtils
import com.fanreact.shoppingcartpricecalculator.utilities.IRecyclerViewItemInteractionListener

class ReceiptProductAdapter(context: Context, recyclerViewItemInteractionListener: IRecyclerViewItemInteractionListener): BaseProductAdapter(context, recyclerViewItemInteractionListener) {
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.tvCost.text = DataConversionUtils.priceDisplayString(products[position].product.totalPriceWithTax())
    }
}