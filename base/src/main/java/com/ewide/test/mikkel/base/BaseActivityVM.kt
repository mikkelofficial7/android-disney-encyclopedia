package com.ewide.test.mikkel.base

import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.viewbinding.ViewBinding
import com.ewide.test.mikkel.base.state.UIState
import kotlin.reflect.KClass

abstract class BaseActivityVM<VB : ViewBinding, VM : BaseViewModel>(clazz: KClass<VM>) : BaseActivity<VB>() {
    protected val baseViewModel: VM by viewModel(clazz = clazz)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel(baseViewModel)
    }

    abstract fun observeViewModel(viewModel: VM)

    internal fun <T>handleResponseState(state: UIState, onSuccessData : (T) -> Unit = {}) {
        when(state) {
            is UIState.OnLoading -> {
                showProgressDialog()
            }
            is UIState.OnFinishLoading -> {
                hideProgressDialog()
            }
            is UIState.OnSuccess<*> -> {
                onSuccessData(state.response as T)
            }
            is UIState.OnFailure -> {
                handleFailure(state.failureState)
            }
        }
    }
}