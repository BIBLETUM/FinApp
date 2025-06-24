package com.yanschool.finapp.domain.today_expenses

import com.yanschool.domain.common_models.TransactionDetail
import com.yanschool.domain.common_models.TransactionShort
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {

    fun getTransactionsShort(
        startDate: String? = null,
        endDate: String? = null,
    ): Flow<Result<List<TransactionShort>>>

    fun getTransactionsDetail(
        startDate: String? = null,
        endDate: String? = null,
    ): Flow<Result<List<TransactionDetail>>>

}