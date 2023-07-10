package com.ewide.test.mikkel.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewide.test.mikkel.base.exception.Failure
import com.ewide.test.mikkel.base.helper.NetworkHandler
import com.ewide.test.mikkel.base.state.UIState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.plus

abstract class BaseViewModel(
    private val networkHandler: NetworkHandler
) : ViewModel() {
    internal val stateLiveData: MutableLiveData<UIState> = MutableLiveData()

    protected fun executeJob(invoke: () -> Unit) {
        when (networkHandler.isNetworkAvailable()) {
            true -> invoke()
            else -> stateLiveData.postValue(UIState.onFailure(Failure.NetworkConnection))
        }
    }

    protected fun safeScopeFun(error :(Throwable) -> Unit) : CoroutineScope{
        return viewModelScope + CoroutineExceptionHandler { coroutineContext, throwable ->
            error.invoke(throwable)
        }
    }
}