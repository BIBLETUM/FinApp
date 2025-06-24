package com.yanschool.core.extensions

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import retrofit2.HttpException

/**
 * Создаёт Flow, который выполняет переданный suspend-блок и повторяет попытку в случае ошибки,
 * если ошибка — это [HttpException] с кодом 500 (внутренняя ошибка сервера),
 * не превышая заданное количество повторов. Возвращает результат в виде [Result].
 *
 * @param maxRetries Максимальное количество попыток повтора (по умолчанию [MAX_RETRIES]).
 * @param retryDelayMillis Задержка между повторами в миллисекундах (по умолчанию [RETRY_DELAY]).
 * @param block Suspend-лямбда, возвращающая значение типа [T].
 * @return Flow, эмитящий [Result.success] при успешном выполнении блока
 *         или [Result.failure] при ошибке после исчерпания попыток.
 */
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
