package com.fanreact.shoppingcartpricecalculator.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.fanreact.shoppingcartpricecalculator.R
import com.fanreact.shoppingcartpricecalculator.purchase.ProductCounter
import com.fanreact.shoppingcartpricecalculator.ui.loadPurchase.DialogLoadPurchase
import com.fanreact.shoppingcartpricecalculator.utilities.IRecyclerViewItemInteractionListener
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var viewModel: MainViewModel? = null

    private var baseProductAdapter: BaseProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        activity?.let {
            baseProductAdapter = BaseProductAdapter(it, object : IRecyclerViewItemInteractionListener {
                override fun onItemClicked(item: Any) {
                    (item as? ProductCounter)?.let {
                        viewModel?.removeProduct(it)
                    }
                }
            })
            view.rvProductsInCart.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = baseProductAdapter
            }
        }

        view.btLoadPurchase.setOnClickListener {
            this@MainFragment.fragmentManager?.let {
                DialogLoadPurchase()
                    .show(it, "LoadPurchase")
            }
        }

        view.btClearPurchase.setOnClickListener {
            viewModel?.clearPurchase()
        }

        view.btAddToCart.setOnClickListener {
            this@MainFragment.fragmentManager?.let {
                DialogAddProductToCart()
                    .show(it, "AddProduct")
            }
        }

        view.btCompletePurchase.setOnClickListener {
            viewModel?.completePurchase()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel?.apply {
            productsLiveData.observe(this@MainFragment, Observer { products ->
                products?.let {
                    baseProductAdapter?.setProducts(products)
                }
            })
            completedPurchaseLiveData.observe(this@MainFragment, Observer {
                if (it != null) {
                    this@MainFragment.fragmentManager?.let {
                        DialogViewReceipt().show(it, "ViewReceipt")
                    }
                }
            })
        }
    }
}
