package com.yanschool.finapp.domain.account_info

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountInfoFlowUseCase @Inject constructor (
    private val repository: AccountInfoRepository,
) : IGetAccountInfoFlowUseCase {
    override operator fun invoke(): Flow<Result<AccountInfo>> {
        return repository.getAccountInfo()
    }
}

interface IGetAccountInfoFlowUseCase {
    operator fun invoke(): Flow<Result<AccountInfo>>
}