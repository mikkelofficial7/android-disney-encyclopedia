package com.ewide.test.disneys.base

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.ewide.test.disneys.base.state.UIState
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragmentVM<VB : ViewBinding, VM : BaseViewModel>(clazz: KClass<VM>) : BaseFragment<VB>() {

    private val baseViewModel: VM by viewModel(clazz = clazz)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel(baseViewModel)
    }

    abstract fun observeViewModel(viewModel: VM)

    internal fun <T>handleUIState(state: UIState, onSuccessData : (T) -> Unit = {}) {
        when(state) {
            is UIState.onLoading -> {
                showProgress()
            }
            is UIState.onFinishLoading -> {
                hideProgress()
            }
            is UIState.onSuccess<*> -> {
                onSuccessData(state.response as T)
            }
            is UIState.onFailure -> {
                handleFailure(state.failure)
            }
        }
    }
}