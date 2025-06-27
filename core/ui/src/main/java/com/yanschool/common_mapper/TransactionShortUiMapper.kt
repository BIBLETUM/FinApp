package com.yanschool.common_mapper

import com.yanschool.common_models.TransactionShortUi
import com.yanschool.domain.common_models.TransactionShort
import javax.inject.Inject

class TransactionShortUiMapper @Inject constructor(
    private val transactionCategoryUiMapper: TransactionCategoryUiMapper,
) {

    fun mapDomainToUi(domain: TransactionShort): TransactionShortUi {
        return TransactionShortUi(
            id = domain.id,
            amount = domain.amount,
            comment = domain.comment,
            category = transactionCategoryUiMapper.mapDomainToUi(domain.category),
        )
    }
}
