package com.yanschool.finapp.domain.transaction_categories

import kotlinx.coroutines.flow.Flow

interface TransactionCategoriesRepository {

    fun getTransactionCategories(): Flow<Result<List<TransactionCategory>>>

}