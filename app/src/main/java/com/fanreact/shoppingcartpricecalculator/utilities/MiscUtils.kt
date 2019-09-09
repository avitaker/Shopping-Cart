package com.fanreact.shoppingcartpricecalculator.utilities

import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment


fun Fragment.getThemifiedInflater(inflater: LayoutInflater, @StyleRes themeResId: Int) : LayoutInflater {
    val contextThemeWrapper = ContextThemeWrapper(activity, themeResId)

    // clone the inflater using the ContextThemeWrapper
    return inflater.cloneInContext(contextThemeWrapper)
}