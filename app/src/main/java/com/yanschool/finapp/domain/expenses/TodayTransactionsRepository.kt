package com.yanschool.finapp.domain.expenses

import com.yanschool.finapp.domain.common_models.TransactionShort
import kotlinx.coroutines.flow.Flow

interface TodayTransactionsRepository {

    fun getTransactions(
        startDate: String,
        endDate: String,
    ): Flow<Result<List<TransactionShort>>>

}