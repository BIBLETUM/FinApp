package com.yanschool.domain.extensions

import com.yanschool.domain.common_models.TransactionDetail
import java.time.Instant

fun Result<List<TransactionDetail>>.sortDescendingByDate()
        : Result<List<TransactionDetail>> {
    return this.map { list ->
        list.sortedByDescending { transaction ->
            Instant.parse(transaction.dateTime)
        }
    }
}

fun Result<List<TransactionDetail>>.filterTransactionsByType(isIncome: Boolean)
        : Result<List<TransactionDetail>> {
    return this.map { list ->
        list.filter {
            it.category.isIncome == isIncome
        }
    }
}