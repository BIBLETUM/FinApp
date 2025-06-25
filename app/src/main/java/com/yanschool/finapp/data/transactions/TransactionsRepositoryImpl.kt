package com.yanschool.finapp.data.transactions

import com.yanschool.data.ApiService
import com.yanschool.domain.common_models.TransactionDetail
import com.yanschool.domain.common_models.TransactionShort
import com.yanschool.domain.common_repository.AccountIdRepository
import com.yanschool.finapp.data.common_mappers.TransactionDetailDtoMapper
import com.yanschool.finapp.data.common_mappers.TransactionMapper
import com.yanschool.finapp.domain.TransactionsRepository
import com.yanschool.utils.extensions.retryFlowWithResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TransactionsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val accountIdRepository: AccountIdRepository,
    private val mapperShot: TransactionMapper,
    private val mapperDetail: TransactionDetailDtoMapper,
) : TransactionsRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getTransactionsShort(
        startDate: String?,
        endDate: String?,
    ): Flow<Result<List<TransactionShort>>> {
        return accountIdRepository.getCurrentAccountIdFlow()
            .filterNotNull()
            .flatMapLatest { accountId ->
                retryFlowWithResult {
                    apiService.getTransactions(
                        accountId = accountId,
                        startDate = startDate,
                        endDate = endDate
                    )
                        .map { mapperShot.mapDtoToDomainShort(it) }
                }
            }
            .flowOn(Dispatchers.IO)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getTransactionsDetail(
        startDate: String?,
        endDate: String?
    ): Flow<Result<List<TransactionDetail>>> {
        return accountIdRepository.getCurrentAccountIdFlow()
            .filterNotNull()
            .flatMapLatest { accountId ->
                retryFlowWithResult {
                    apiService.getTransactions(
                        accountId = accountId,
                        startDate = startDate,
                        endDate = endDate
                    )
                        .map { mapperDetail.mapDtoToDomain(it) }
                }
            }
            .flowOn(Dispatchers.IO)
    }
}