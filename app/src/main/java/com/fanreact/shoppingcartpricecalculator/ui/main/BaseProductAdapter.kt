package com.fanreact.shoppingcartpricecalculator.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fanreact.shoppingcartpricecalculator.R
import com.fanreact.shoppingcartpricecalculator.product.Product
import com.fanreact.shoppingcartpricecalculator.utilities.DataConversionUtils
import kotlinx.android.synthetic.main.item_product.view.*

open class BaseProductAdapter(private val context: Context) : RecyclerView.Adapter<BaseProductAdapter.ProductViewHolder>(){
    protected val products = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false))
    }

    override fun getItemCount(): Int {
        return products.count()
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        holder.apply {
            tvName.text = product.name
            tvCost.text = DataConversionUtils.priceDisplayString(product.baseCost)
            tvCount.text = context.getString(R.string.format_quantity, 1)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setProducts(products: List<Product>) {
        this.products.apply {
            clear()
            addAll(products)
            notifyDataSetChanged()
        }
    }

    fun addProduct(product: Product, position: Int = products.count()) {
        val translatedPosition = if (position !in 0 until products.count()) {
            products.count()
        } else {
            position
        }
        this.products.apply {
            add(translatedPosition, product)
            notifyItemInserted(translatedPosition)
        }
    }

    fun removeProduct(id: String) {
        products.firstOrNull { p -> p.id == id }?.let {
            val index = products.indexOf(it)
            products.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.tvName
        val tvCost = itemView.tvCost
        val tvCount = itemView.tvCount
    }
}