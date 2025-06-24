package com.yanschool.finapp.data.transaction_categories

import com.yanschool.finapp.data.ApiService
import com.yanschool.finapp.data.common_mappers.CategoryStatDtoMapper
import com.yanschool.finapp.domain.common_models.TransactionCategory
import com.yanschool.finapp.domain.splash.AccountIdRepository
import com.yanschool.finapp.domain.transaction_categories.TransactionCategoriesRepository
import com.yanschool.utils.extensions.retryFlowWithResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TransactionCategoriesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val accountIdRepository: AccountIdRepository,
    private val mapper: CategoryStatDtoMapper,
) : TransactionCategoriesRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getTransactionCategories(): Flow<Result<List<TransactionCategory>>> {
        return accountIdRepository.getCurrentAccountId()
            .filterNotNull()
            .flatMapLatest { accountId ->
                retryFlowWithResult {
                    apiService.getAccountInfo(accountId).expenseStats
                        .map { mapper.mapDtoToDomain(it, false) }
                }
            }
            .flowOn(Dispatchers.IO)
    }

}
