package com.yanschool.domain.common_repository

import com.yanschool.domain.common_models.TransactionDetail
import com.yanschool.domain.common_models.TransactionShort
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {

    fun getTransactionsShort(
        accountId: Int,
        startDate: String? = null,
        endDate: String? = null,
    ): Flow<Result<List<TransactionShort>>>

    fun getTransactionsDetail(
        accountId: Int,
        startDate: String? = null,
        endDate: String? = null,
    ): Flow<Result<List<TransactionDetail>>>

}