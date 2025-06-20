package com.yanschool.finapp.domain.transaction_categories

import com.yanschool.finapp.domain.common_models.TransactionCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionCategoriesFlowUseCase @Inject constructor(
    private val repository: TransactionCategoriesRepository,
) : IGetTransactionCategoriesFlowUseCase {

    override fun invoke(): Flow<Result<List<TransactionCategory>>> {
        return repository.getTransactionCategories()
    }

}

interface IGetTransactionCategoriesFlowUseCase {
    operator fun invoke(): Flow<Result<List<TransactionCategory>>>
}