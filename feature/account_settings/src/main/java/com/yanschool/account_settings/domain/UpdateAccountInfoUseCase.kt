package com.yanschool.account_settings.domain

import com.yanschool.domain.common_models.AccountInfo
import com.yanschool.domain.common_repository.CurrentAccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpdateAccountInfoUseCase @Inject constructor(
    private val accountSettingsRepository: AccountSettingsRepository,
    private val currentAccountRepository: CurrentAccountRepository,
) : IUpdateAccountInfoUseCase {

    override suspend fun invoke(account: AccountInfo): Flow<Result<Unit>> {
        return accountSettingsRepository.updateAccount(account)
            .map { result ->
                result.fold(
                    onSuccess = {
                        currentAccountRepository.setNewAccountInfo(it)
                        Result.success(Unit)
                    },
                    onFailure = {
                        Result.failure(it)
                    }
                )
            }
    }
}

interface IUpdateAccountInfoUseCase {
    suspend fun invoke(account: AccountInfo): Flow<Result<Unit>>
}
