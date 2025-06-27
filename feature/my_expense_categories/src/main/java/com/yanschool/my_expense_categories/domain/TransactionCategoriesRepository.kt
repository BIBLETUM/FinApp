package com.yanschool.my_expense_categories.domain

import com.yanschool.domain.common_models.TransactionCategory
import kotlinx.coroutines.flow.Flow

/**
 * Репозиторий для получения списка категорий транзакций для указанного аккаунта.
 */
interface TransactionCategoriesRepository {

    /**
     * Возвращает поток с результатом загрузки списка категорий транзакций.
     *
     * @param accountId идентификатор аккаунта, для которого запрашиваются категории транзакций
     * @return [Flow] с [Result], содержащим список категорий или ошибку загрузки
     */
    fun getTransactionCategories(accountId: Int): Flow<Result<List<TransactionCategory>>>
}
