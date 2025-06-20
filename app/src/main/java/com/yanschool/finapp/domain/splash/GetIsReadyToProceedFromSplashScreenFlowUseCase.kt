package com.yanschool.finapp.domain.splash

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetIsReadyToProceedFromSplashScreenFlowUseCase @Inject constructor(
    private val accountIdRepository: AccountIdRepository,
) : IGetIsReadyToProceedFromSplashScreenFlowUseCase {

    override operator fun invoke(): Flow<Boolean> {
        return accountIdRepository.getCurrentAccountId().map { it != null }
    }

    override suspend fun loadAccountId() {
        accountIdRepository.loadAccountId()
    }

}

interface IGetIsReadyToProceedFromSplashScreenFlowUseCase {

    operator fun invoke(): Flow<Boolean>

    suspend fun loadAccountId()

}