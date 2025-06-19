package com.yanschool.finapp.data.expenses

import com.yanschool.core.extensions.retryFlowWithResult
import com.yanschool.finapp.data.ApiService
import com.yanschool.finapp.data.common_mappers.TransactionMapper
import com.yanschool.finapp.domain.common_models.TransactionShort
import com.yanschool.finapp.domain.expenses.TodayTransactionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TransactionsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: TransactionMapper,
) : TodayTransactionsRepository {

    override fun getTransactions(
        startDate: String,
        endDate: String,
    ): Flow<Result<List<TransactionShort>>> {
        return retryFlowWithResult {
            apiService.getTransactions(
                startDate = startDate,
                endDate = endDate
            )
                .map { mapper.mapDtoToDomain(it) }
        }
            .flowOn(Dispatchers.IO)
    }
}