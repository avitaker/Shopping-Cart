package com.fanreact.shoppingcartpricecalculator.ui.loadPurchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.fanreact.shoppingcartpricecalculator.R
import com.fanreact.shoppingcartpricecalculator.purchase.Purchase
import com.fanreact.shoppingcartpricecalculator.purchase.PurchaseCache
import com.fanreact.shoppingcartpricecalculator.ui.main.MainViewModel
import com.fanreact.shoppingcartpricecalculator.utilities.IRecyclerViewItemInteractionListener
import com.fanreact.shoppingcartpricecalculator.utilities.getThemifiedInflater
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_load_purchase.view.*

class DialogLoadPurchase : BottomSheetDialogFragment(), IRecyclerViewItemInteractionListener {
    override fun onItemClicked(item: Any) {
        (item as? Purchase)?.let {
            mainViewModel?.seedPurchase(it)
            dismiss()
        }
    }

    private var mainViewModel: MainViewModel? =  null

    private var purchaseSummaryAdapter: PurchaseSummaryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) dismiss()
        activity?.let {
            mainViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = getThemifiedInflater(inflater, R.style.AppTheme).inflate(R.layout.dialog_load_purchase, container, false)

        view.rvPurchases.apply {
            layoutManager = LinearLayoutManager(context)
            purchaseSummaryAdapter = PurchaseSummaryAdapter(context, this@DialogLoadPurchase)
            adapter = purchaseSummaryAdapter
        }

        purchaseSummaryAdapter?.setPurchases(PurchaseCache.values().toList())

        return view
    }
}