package com.yanschool.my_expense_categories.domain

import com.yanschool.domain.common_models.TransactionCategory
import com.yanschool.domain.common_usecase.IGetAccountIdFlowUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class GetTransactionCategoriesFlowUseCase @Inject constructor(
    private val repository: TransactionCategoriesRepository,
    private val accountIdFlowUseCase: IGetAccountIdFlowUseCase,
) : IGetTransactionCategoriesFlowUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(): Flow<Result<List<TransactionCategory>>> {
        return accountIdFlowUseCase.invoke()
            .filterNotNull()
            .flatMapLatest { accountId ->
                repository.getTransactionCategories(accountId)
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