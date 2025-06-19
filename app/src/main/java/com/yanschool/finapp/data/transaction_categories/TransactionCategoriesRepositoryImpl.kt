package com.yanschool.finapp.data.transaction_categories

import com.yanschool.core.extensions.retryFlowWithResult
import com.yanschool.finapp.data.ApiService
import com.yanschool.finapp.data.common_mappers.CategoryStatDtoMapper
import com.yanschool.finapp.domain.common_models.TransactionCategory
import com.yanschool.finapp.domain.transaction_categories.TransactionCategoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TransactionCategoriesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: CategoryStatDtoMapper,
) : TransactionCategoriesRepository {

    override fun getTransactionCategories(): Flow<Result<List<TransactionCategory>>> {
        return retryFlowWithResult {
            apiService.getAccountInfo().expenseStats
                .map { mapper.mapDtoToDomain(it, false) }
        }
            .flowOn(Dispatchers.IO)
    }

}
