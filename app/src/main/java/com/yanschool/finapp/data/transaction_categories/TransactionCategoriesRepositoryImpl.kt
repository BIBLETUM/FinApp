package com.yanschool.finapp.data.transaction_categories

import com.yanschool.core.extensions.retryFlowWithResult
import com.yanschool.finapp.data.ApiService
import com.yanschool.finapp.domain.transaction_categories.TransactionCategoriesRepository
import com.yanschool.finapp.domain.transaction_categories.TransactionCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TransactionCategoriesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: TransactionCategoryDtoMapper,
) : TransactionCategoriesRepository {

    override fun getTransactionCategories(): Flow<Result<List<TransactionCategory>>> {
        return retryFlowWithResult {
            apiService.getTransactionCategories()
                .filter { category ->
                    !category.isIncome
                }
                .map { mapper.mapDtoToDomain(it) }
        }
            .flowOn(Dispatchers.IO)
    }

}
