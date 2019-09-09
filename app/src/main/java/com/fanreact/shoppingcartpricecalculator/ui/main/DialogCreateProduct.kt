package com.fanreact.shoppingcartpricecalculator.ui.main

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.StyleRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fanreact.shoppingcartpricecalculator.R
import com.fanreact.shoppingcartpricecalculator.product.ProductCache
import com.fanreact.shoppingcartpricecalculator.product.ProductCategory
import com.fanreact.shoppingcartpricecalculator.product.displayString
import com.fanreact.shoppingcartpricecalculator.product.parseFromDisplayString
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_create_product.view.*

class DialogCreateProduct : BottomSheetDialogFragment() {
    private lateinit var createProductViewModel: CreateProductViewModel
    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            dismiss()
        }
        createProductViewModel = ViewModelProviders.of(this).get(CreateProductViewModel::class.java)
        activity?.let {
            mainViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = getThemifiedInflater(inflater, R.style.AppTheme).inflate(R.layout.dialog_create_product, container, false)

        view.tietName.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                p0?.toString()?.let {
                    createProductViewModel.setName(it)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        val categoriesSpinner = view.categoriesSpinner
        categoriesSpinner.adapter = ArrayAdapter(categoriesSpinner.context,
            android.R.layout.simple_spinner_dropdown_item, displayStringList(categoriesSpinner.context))
        categoriesSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val item = categoriesSpinner.selectedItem.toString()

                val pc = parseFromDisplayString(categoriesSpinner.context, item)

                createProductViewModel.setCategory(pc)
            }
        }

        val tietCost = view.tietCost
        tietCost.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                p0?.toString()?.let {
                    try {
                        createProductViewModel.setCost(it)
                    } catch (e: Exception) {
                        tietCost?.error = "Invalid"
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tietCost?.error = null
            }
        })

        view.switchImported.setOnCheckedChangeListener { _, b ->
            createProductViewModel.setIsImported(b)
        }

        view.btSubmit.setOnClickListener {
            createProductViewModel.submitProduct()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        createProductViewModel.createdProductLiveData.observe(this, Observer {
            it?.let {
                ProductCache.put(it)
                activity?.let {
                    Toast.makeText(it, "Product Created Successfully", Toast.LENGTH_LONG).show()
                }
                dismiss()
            }
        })
    }

    fun getThemifiedInflater(inflater: LayoutInflater, @StyleRes themeResId: Int) : LayoutInflater {
        val contextThemeWrapper = ContextThemeWrapper(activity, themeResId)

        // clone the inflater using the ContextThemeWrapper
        return inflater.cloneInContext(contextThemeWrapper)
    }

    fun displayStringList(context: Context) : List<String> {
        val listOfString = mutableListOf<String>()
        ProductCategory.values().forEach {
            listOfString.add(it.displayString(context))
        }
        return listOfString
    }
}