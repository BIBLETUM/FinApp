package com.yanschool.splash.domain

import com.yanschool.domain.common_repository.AccountIdRepository
import com.yanschool.domain.common_usecase.IGetAccountIdFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetIsReadyToProceedFromSplashScreenFlowInteractor @Inject constructor(
    private val getAccountIdFlowUseCase: IGetAccountIdFlowUseCase,
    private val accountIdRepository: AccountIdRepository,
) : IGetIsReadyToProceedFromSplashScreenFlowInteractor {

    override operator fun invoke(): Flow<Boolean> {
        return getAccountIdFlowUseCase().map { it != null }
    }

    override suspend fun loadAccountId() {
        accountIdRepository.loadAccountId()
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