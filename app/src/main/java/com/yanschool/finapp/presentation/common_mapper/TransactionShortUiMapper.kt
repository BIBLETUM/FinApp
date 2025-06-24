package com.yanschool.finapp.presentation.common_mapper

import com.yanschool.domain.common_models.TransactionShort
import com.yanschool.finapp.presentation.model.TransactionShortUi
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