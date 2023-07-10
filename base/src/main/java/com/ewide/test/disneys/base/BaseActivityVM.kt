package com.ewide.test.disneys.base

import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

abstract class BaseActivityVM<VB : ViewBinding, VM : BaseViewModel>(clazz: KClass<VM>) : BaseActivity<VB>() {

    val baseViewModel: VM by viewModel(clazz = clazz)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel(baseViewModel)
    }

    abstract fun observeViewModel(viewModel: VM)

    protected fun handleLoading(showLoading: Boolean?) {
        if (showLoading == true) showProgressDialog() else hideProgressDialog()
    }
}