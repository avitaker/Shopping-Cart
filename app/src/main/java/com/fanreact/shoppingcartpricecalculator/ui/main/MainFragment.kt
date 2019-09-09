package com.fanreact.shoppingcartpricecalculator.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fanreact.shoppingcartpricecalculator.R
import com.fanreact.shoppingcartpricecalculator.purchase.PurchaseSeeder
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var viewModel: MainViewModel? = null

    private var shoppingCartProductAdapter: ShoppingCartProductAdapter? = null

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
            shoppingCartProductAdapter = ShoppingCartProductAdapter(it)
            view.rvProductsInCart.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = shoppingCartProductAdapter
            }
        }

        view.fabAddProduct.setOnClickListener {
            this@MainFragment.fragmentManager?.let {
                DialogCreateProduct().show(it, null)
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel?.apply {
            productsLiveData.observe(this@MainFragment, Observer { products ->
                products?.let {
                    shoppingCartProductAdapter?.setProducts(products)
                }
            })
            seedPurchase(PurchaseSeeder.Purchase2)
        }
    }
}
