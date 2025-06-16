package com.yanschool.core.extensions

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import retrofit2.HttpException

inline fun <T> retryFlowWithResult(
    crossinline block: suspend () -> T
): Flow<Result<T>> {
    return flow {
        emit(Result.success(block()))
    }.retryWhen { cause, attempt ->
        if (cause is HttpException && cause.code() == 500 && attempt < 3) {
            delay(2000)
            true
        } else {
            false
        }
    }.catch { e ->
        emit(Result.failure(e))
    }
}
