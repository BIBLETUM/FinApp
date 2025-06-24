package com.yanschool.core.extensions

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import retrofit2.HttpException

inline fun <T> retryFlowWithResult(
    maxRetries: Int = MAX_RETRIES,
    retryDelayMillis: Long = RETRY_DELAY,
    crossinline block: suspend () -> T
): Flow<Result<T>> {
    return flow {
        emit(Result.success(block()))
    }.retryWhen { cause, attempt ->
        if (cause is HttpException && cause.code() == INTERNAL_SERVER_ERROR && attempt < maxRetries) {
            delay(retryDelayMillis)
            true
        } else {
            false
        }
    }.catch { e ->
        emit(Result.failure(e))
    }
}

const val MAX_RETRIES = 3
const val RETRY_DELAY = 2000L
const val INTERNAL_SERVER_ERROR = 500
