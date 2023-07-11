package com.ewide.test.mikkel.base.state

sealed class FailureState {
    object DataNotFound : FailureState()
    object NetworkConnection : FailureState()
    object ServerError : FailureState()
    data class Other(val code: String, val message: String) : FailureState()
}