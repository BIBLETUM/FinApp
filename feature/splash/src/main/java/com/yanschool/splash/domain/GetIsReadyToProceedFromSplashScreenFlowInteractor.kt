package com.yanschool.splash.domain

import com.yanschool.domain.common_repository.CurrentAccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetIsReadyToProceedFromSplashScreenFlowInteractor @Inject constructor(
    private val currentAccountRepository: CurrentAccountRepository,
) : IGetIsReadyToProceedFromSplashScreenFlowInteractor {

    override operator fun invoke(): Flow<Boolean> {
        return currentAccountRepository.getCurrentAccountFlow().map { it != null }
    }

    override suspend fun loadAccountId() {
        currentAccountRepository.loadAccount()
    }
}

/**
 * Интерфейс интерактора для проверки готовности к переходу с экрана загрузки.
 */
interface IGetIsReadyToProceedFromSplashScreenFlowInteractor {

    /**
     * Возвращает поток, эмитирующий флаг готовности (true, если ID аккаунта загружен).
     */
    operator fun invoke(): Flow<Boolean>

    /**
     * Запускает процесс загрузки идентификатора аккаунта.
     */
    suspend fun loadAccountId()
}
