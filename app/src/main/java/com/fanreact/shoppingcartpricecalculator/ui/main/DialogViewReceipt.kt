package com.fanreact.shoppingcartpricecalculator.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.fanreact.shoppingcartpricecalculator.R
import com.fanreact.shoppingcartpricecalculator.utilities.DataConversionUtils
import com.fanreact.shoppingcartpricecalculator.utilities.IRecyclerViewItemInteractionListener
import com.fanreact.shoppingcartpricecalculator.utilities.getThemifiedInflater
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_view_receipt.view.*

class DialogViewReceipt : BottomSheetDialogFragment() {
    private var mainViewModel: MainViewModel? = null

    private var receiptProductAdapter: ReceiptProductAdapter? = null
    private var tvSalesTax: TextView? = null
    private var tvTotal: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            dismiss()
        }
        activity?.let {
            mainViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = getThemifiedInflater(inflater, R.style.AppTheme).inflate(R.layout.dialog_view_receipt, container, false)

        tvSalesTax = view.tvSalesTax
        tvTotal = view.tvTotalPrice

        view.rvProductsInReceipt.apply {
            layoutManager = LinearLayoutManager(context)
            receiptProductAdapter = ReceiptProductAdapter(context, object: IRecyclerViewItemInteractionListener {
                override fun onItemClicked(item: Any) {
                }
            })
            adapter = receiptProductAdapter
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        mainViewModel?.apply {
            completedPurchaseLiveData.observe(this@DialogViewReceipt, Observer {
                if (it != null) {
                    receiptProductAdapter?.setProducts(it.products().toList())
                    tvSalesTax?.text = DataConversionUtils.priceDisplayString(it.totalSalesTax)
                    tvTotal?.text = DataConversionUtils.priceDisplayString(it.totalPriceWithTax)
                }
            })
        }
    }
}