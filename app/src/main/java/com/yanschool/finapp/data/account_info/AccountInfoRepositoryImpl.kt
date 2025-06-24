package com.yanschool.finapp.data.account_info


import com.yanschool.data.ApiService
import com.yanschool.domain.common_repository.AccountIdRepository
import com.yanschool.finapp.data.common_mappers.AccountInfoMapper
import com.yanschool.finapp.domain.account_info.AccountInfo
import com.yanschool.finapp.domain.account_info.AccountInfoRepository
import com.yanschool.utils.extensions.retryFlowWithResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AccountInfoRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val accountIdRepository: AccountIdRepository,
    private val mapper: AccountInfoMapper,
) : AccountInfoRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAccountInfo(): Flow<Result<AccountInfo>> {
        return accountIdRepository.getCurrentAccountId()
            .filterNotNull()
            .flatMapLatest { accountId ->
                retryFlowWithResult {
                    val dto = apiService.getAccountInfo(accountId)
                    mapper.mapDtoToDomain(dto)
                }
            }
            .flowOn(Dispatchers.IO)
    }
}