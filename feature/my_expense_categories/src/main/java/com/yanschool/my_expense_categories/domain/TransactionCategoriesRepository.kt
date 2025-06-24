package com.yanschool.my_expense_categories.domain

import com.yanschool.domain.common_models.TransactionCategory
import kotlinx.coroutines.flow.Flow

interface TransactionCategoriesRepository {

    fun getTransactionCategories(accountId: Int): Flow<Result<List<TransactionCategory>>>

}