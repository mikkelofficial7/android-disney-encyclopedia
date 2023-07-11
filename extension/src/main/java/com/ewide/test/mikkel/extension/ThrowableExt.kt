package com.ewide.test.mikkel.extension

import com.ewide.test.mikkel.base.state.FailureState
import retrofit2.HttpException
import java.io.IOException

fun Throwable.getGeneralError(): FailureState {
    return when (this) {
        is HttpException -> {
            try {
                if (this.code() >= 500) {
                    return FailureState.ServerError
                }
                if (this.code() >= 404) {
                    return FailureState.DataNotFound
                }
                FailureState.Other(this.response()?.code().toString(), this.response()?.message().orEmpty())
            } catch (e: Exception) {
                FailureState.NetworkConnection
            }
        }
        is IOException -> FailureState.NetworkConnection
        else -> FailureState.NetworkConnection
    }
}