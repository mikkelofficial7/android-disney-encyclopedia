package com.ewide.test.mikkel.base.state

sealed class UIState {
    object OnLoading : UIState()
    object OnFinishLoading : UIState()
    class OnSuccess<T>(val response: T) : UIState()
    class OnFailure(val failureState: FailureState) : UIState()
}