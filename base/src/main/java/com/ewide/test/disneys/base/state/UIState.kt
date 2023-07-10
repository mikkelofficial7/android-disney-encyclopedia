package com.ewide.test.disneys.base.state

import com.ewide.test.disneys.base.exception.Failure

sealed class UIState {
    object onLoading : UIState()
    object onFinishLoading : UIState()
    class onSuccess<T>(val response: T) : UIState()
    class onFailure(val failure: Failure) : UIState()
}