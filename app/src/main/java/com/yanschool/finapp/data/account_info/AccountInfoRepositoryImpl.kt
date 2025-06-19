package com.yanschool.finapp.data.account_info

import com.yanschool.core.extensions.retryFlowWithResult
import com.yanschool.finapp.data.ApiService
import com.yanschool.finapp.data.common_mappers.AccountInfoMapper
import com.yanschool.finapp.domain.account_info.AccountInfo
import com.yanschool.finapp.domain.account_info.AccountInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AccountInfoRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: AccountInfoMapper,
) : AccountInfoRepository {

    override fun getAccountInfo(): Flow<Result<AccountInfo>> {
        return retryFlowWithResult {
            mapper.mapDtoToDomain(apiService.getAccountInfo())
        }
            .flowOn(Dispatchers.IO)
    }
}