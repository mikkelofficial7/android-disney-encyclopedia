package com.ewide.test.mikkel.base

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.ewide.test.mikkel.base.state.FailureState
import com.ewide.test.mikkel.base.state.UIState
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragmentVM<VB : ViewBinding, VM : BaseViewModel>(clazz: KClass<VM>) : BaseFragment<VB>() {
    protected val baseViewModel: VM by viewModel(clazz = clazz)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel(baseViewModel)
    }

    abstract fun observeViewModel(viewModel: VM)

    fun <T>handleResponseState(state: UIState?, onSuccessData : (T) -> Unit = {}) {
        when(state) {
            is UIState.OnLoading -> {
                showProgress()
            }
            is UIState.OnFinishLoading -> {
                hideProgress()
            }
            is UIState.OnSuccess<*> -> {
                onSuccessData(state.response as T)
            }
            is UIState.OnFailure -> {
                handleFailure(state.failureState)
            }
            else -> {
                handleFailure(FailureState.DataNotFound)
            }
        }
    }
}