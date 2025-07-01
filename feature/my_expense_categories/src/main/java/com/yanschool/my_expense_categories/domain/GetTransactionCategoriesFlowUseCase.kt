package com.yanschool.my_expense_categories.domain

import com.yanschool.domain.common_models.TransactionCategory
import com.yanschool.domain.common_usecase.IGetCurrentAccountFlowUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class GetTransactionCategoriesFlowUseCase @Inject constructor(
    private val repository: TransactionCategoriesRepository,
    private val accountIdFlowUseCase: IGetCurrentAccountFlowUseCase,
    private val searchFlowUseCase: InnerSearchFlow,
) : IGetTransactionCategoriesFlowUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(): Flow<Result<List<TransactionCategory>>> {
        val cachedCategoriesFlow = accountIdFlowUseCase.invoke()
            .flatMapLatest { account ->
                repository.getTransactionCategories(account.id)
            }

        return combine(cachedCategoriesFlow, searchFlowUseCase.getSearchFlow()) { result, query ->
            filterResultByQuery(result, query)
        }
    }

    private fun filterResultByQuery(
        result: Result<List<TransactionCategory>>,
        query: String
    ): Result<List<TransactionCategory>> {
        return if (query.isBlank()) {
            result
        } else {
            result.map { categories ->
                categories.filter {
                    it.name.contains(
                        query,
                        ignoreCase = true
                    )
                }
            }
        }
    }
}

/**
 * Интерфейс use case для получения потока категорий транзакций.
 */
interface IGetTransactionCategoriesFlowUseCase {

    /**
     * Возвращает поток с результатом загрузки списка категорий транзакций
     * текущего аккаунта.
     *
     * @return [Flow] с [Result], содержащим список категорий или ошибку
     */
    operator fun invoke(): Flow<Result<List<TransactionCategory>>>
}
