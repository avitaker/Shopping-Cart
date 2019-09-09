package com.fanreact.shoppingcartpricecalculator.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fanreact.shoppingcartpricecalculator.R
import com.fanreact.shoppingcartpricecalculator.ui.createProduct.DialogCreateProduct
import com.fanreact.shoppingcartpricecalculator.utilities.getThemifiedInflater
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_add_product.view.*

class DialogAddProductToCart : BottomSheetDialogFragment() {
    private lateinit var addProductToCartViewModel: AddProductToCartViewModel
    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            dismiss()
        }
        addProductToCartViewModel = ViewModelProviders.of(this).get(AddProductToCartViewModel::class.java)
        activity?.let {
            mainViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = getThemifiedInflater(inflater, R.style.AppTheme).inflate(R.layout.dialog_add_product, container, false)

        val productsSpinner = view.spinnerProduct
        productsSpinner.adapter = ArrayAdapter(productsSpinner.context,
            android.R.layout.simple_spinner_dropdown_item, addProductToCartViewModel.displayProductNames())
        productsSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val item = productsSpinner.selectedItem.toString()

                addProductToCartViewModel.setProductWithName(item)
            }
        }


        val quantitiesSpinner = view.spinnerQuantity
        quantitiesSpinner.adapter = ArrayAdapter(quantitiesSpinner.context,
            android.R.layout.simple_spinner_dropdown_item, addProductToCartViewModel.displayQuantities())
        quantitiesSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val item = quantitiesSpinner.selectedItem.toString()

                addProductToCartViewModel.setQuantity(item.toInt())
            }
        }

        view.btSubmit.setOnClickListener {
            addProductToCartViewModel.addSelectedToCart()
        }

        view.btCreateProduct.setOnClickListener {
            this@DialogAddProductToCart.fragmentManager?.let {
                DialogCreateProduct().show(it, "CreateProduct")
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        addProductToCartViewModel.finalProductCounterLiveData.observe(this, Observer {
            if (it != null) {
                mainViewModel?.addProduct(it)
                dismiss()
            }
        })
    }
}