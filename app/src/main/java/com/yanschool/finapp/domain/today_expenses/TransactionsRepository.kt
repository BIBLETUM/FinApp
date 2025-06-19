package com.yanschool.finapp.domain.today_expenses

import com.yanschool.finapp.domain.common_models.TransactionShort
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {

    fun getTransactions(
        startDate: String? = null,
        endDate: String? = null,
    ): Flow<Result<List<TransactionShort>>>

}