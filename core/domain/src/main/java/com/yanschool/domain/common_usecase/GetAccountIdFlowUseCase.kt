package com.yanschool.domain.common_usecase

import com.yanschool.domain.common_repository.AccountIdRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAccountIdFlowUseCase @Inject constructor(
    private val accountIdRepository: AccountIdRepository,
) : IGetAccountIdFlowUseCase {

    override fun invoke(): Flow<Int?> {
        return accountIdRepository.getCurrentAccountIdFlow()
    }
}

interface IGetAccountIdFlowUseCase {

    operator fun invoke(): Flow<Int?>

}