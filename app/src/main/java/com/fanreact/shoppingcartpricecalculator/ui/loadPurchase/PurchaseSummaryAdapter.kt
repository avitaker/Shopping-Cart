package com.fanreact.shoppingcartpricecalculator.ui.loadPurchase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fanreact.shoppingcartpricecalculator.R
import com.fanreact.shoppingcartpricecalculator.purchase.Purchase
import com.fanreact.shoppingcartpricecalculator.utilities.IRecyclerViewItemInteractionListener
import kotlinx.android.synthetic.main.item_purchase.view.*

class PurchaseSummaryAdapter(private val context: Context, private val recyclerViewItemInteractionListener: IRecyclerViewItemInteractionListener) : RecyclerView.Adapter<PurchaseSummaryAdapter.PurchaseSummaryViewHolder>() {
    private val purchases = mutableListOf<Purchase>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseSummaryViewHolder {
        return PurchaseSummaryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_purchase, parent, false))
    }

    override fun getItemCount(): Int {
        return purchases.count()
    }

    override fun onBindViewHolder(holder: PurchaseSummaryViewHolder, position: Int) {
        val purchase = purchases[position]

        holder.tvPurchaseSummary.text = generateSummaryString(purchase)

        holder.itemView.setOnClickListener { recyclerViewItemInteractionListener.onItemClicked(purchase) }
    }

    private fun generateSummaryString(purchase: Purchase) : String {
        var summaryString = ""
        purchase.products().forEach {
            summaryString += it.product.name + ", "
        }

        try {
            return summaryString.substring(0, summaryString.count() - 2)
        } catch (e: Exception) {
            return ""
        }
    }

    fun setPurchases(purchases: List<Purchase>) {
        this.purchases.apply {
            clear()
            addAll(purchases)
            notifyDataSetChanged()
        }
    }

    class PurchaseSummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPurchaseSummary = itemView.tvPurchaseSummary
    }
}