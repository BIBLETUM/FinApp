package com.yanschool.finapp.domain.transaction_categories

import com.yanschool.finapp.domain.common_models.TransactionCategory
import kotlinx.coroutines.flow.Flow

interface TransactionCategoriesRepository {

    fun getTransactionCategories(): Flow<Result<List<TransactionCategory>>>

}