package com.yanschool.account_info.data

import com.yanschool.account_info.domain.AccountInfo
import com.yanschool.account_info.domain.AccountInfoRepository
import com.yanschool.data.remote_data_source.api.AccountInfoService
import com.yanschool.utils.extensions.retryFlowWithResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AccountInfoRepositoryImpl @Inject constructor(
    private val accountInfoService: AccountInfoService,
    private val mapper: AccountInfoMapper,
) : AccountInfoRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAccountInfo(accountId: Int): Flow<Result<AccountInfo>> {
        return retryFlowWithResult {
            val dto = accountInfoService.getAccountInfo(accountId)
            mapper.mapDtoToDomain(dto)
        }
            .flowOn(Dispatchers.IO)
    }
}
