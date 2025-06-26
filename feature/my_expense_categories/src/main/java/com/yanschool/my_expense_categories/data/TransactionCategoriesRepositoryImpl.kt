package com.yanschool.my_expense_categories.data

import com.yanschool.data.remote_data_source.api.AccountInfoService
import com.yanschool.domain.common_models.TransactionCategory
import com.yanschool.my_expense_categories.domain.TransactionCategoriesRepository
import com.yanschool.utils.extensions.retryFlowWithResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TransactionCategoriesRepositoryImpl @Inject constructor(
    private val accountInfoService: AccountInfoService,
    private val mapper: CategoryStatDtoMapper,
) : TransactionCategoriesRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getTransactionCategories(accountId: Int): Flow<Result<List<TransactionCategory>>> {
        return retryFlowWithResult {
            accountInfoService.getAccountInfo(accountId).expenseStats
                .map { mapper.mapDtoToDomain(it, false) }
        }
            .flowOn(Dispatchers.IO)
    }
}
