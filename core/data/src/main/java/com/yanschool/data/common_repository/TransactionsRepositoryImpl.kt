package com.yanschool.data.common_repository

import com.yanschool.data.remote_data_source.api.TransactionsService
import com.yanschool.data.remote_data_source.common_mappers.TransactionDetailDtoMapper
import com.yanschool.data.remote_data_source.common_mappers.TransactionMapper
import com.yanschool.domain.common_models.TransactionDetail
import com.yanschool.domain.common_models.TransactionShort
import com.yanschool.domain.common_repository.TransactionsRepository
import com.yanschool.utils.extensions.retryFlowWithResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TransactionsRepositoryImpl @Inject constructor(
    private val transactionsService: TransactionsService,
    private val mapperShot: TransactionMapper,
    private val mapperDetail: TransactionDetailDtoMapper,
) : TransactionsRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getTransactionsShort(
        accountId: Int,
        startDate: String?,
        endDate: String?,
    ): Flow<Result<List<TransactionShort>>> {
        return retryFlowWithResult {
            transactionsService.getTransactions(
                accountId = accountId,
                startDate = startDate,
                endDate = endDate
            )
                .map { mapperShot.mapDtoToDomainShort(it) }

        }
            .flowOn(Dispatchers.IO)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getTransactionsDetail(
        accountId: Int,
        startDate: String?,
        endDate: String?
    ): Flow<Result<List<TransactionDetail>>> {
        return retryFlowWithResult {
            transactionsService.getTransactions(
                accountId = accountId,
                startDate = startDate,
                endDate = endDate
            )
                .map { mapperDetail.mapDtoToDomain(it) }
        }
            .flowOn(Dispatchers.IO)
    }
}