package com.yanschool.account_settings.data

import com.yanschool.account_settings.domain.AccountSettingsRepository
import com.yanschool.data.remote_data_source.api.AccountSettingsService
import com.yanschool.data.remote_data_source.common_mappers.AccountShortMapper
import com.yanschool.domain.common_models.AccountInfo
import com.yanschool.utils.extensions.retryFlowWithResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountSettingsRepositoryImpl @Inject constructor(
    private val accountSettingsService: AccountSettingsService,
    private val accountShortMapper: AccountShortMapper,
    private val accountSettingsMapper: AccountSettingsMapper,
) : AccountSettingsRepository {

    override fun updateAccount(account: AccountInfo): Flow<Result<AccountInfo>> {
        return retryFlowWithResult {
            accountSettingsService.updateAccount(
                accountId = account.id,
                accountSettingsDto = accountSettingsMapper.mapDomainToDto(account)
            )
        }
            .map { result -> result.map { accountShortMapper.mapDtoToDomain(it) } }
            .flowOn(Dispatchers.IO)
    }
}
