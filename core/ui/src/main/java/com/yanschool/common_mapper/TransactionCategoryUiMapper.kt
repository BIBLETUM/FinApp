package com.yanschool.common_mapper

import com.yanschool.common_models.TransactionCategoryUi
import com.yanschool.common_models.TransactionTypeUi
import com.yanschool.domain.common_models.TransactionCategory
import javax.inject.Inject

class TransactionCategoryUiMapper @Inject constructor() {
    fun mapDomainToUi(domain: TransactionCategory): TransactionCategoryUi {
        return TransactionCategoryUi(
            id = domain.id,
            name = domain.name,
            emoji = domain.emoji,
            literals = domain.name.take(FIRST_TWO_LETTERS),
            type = if (domain.isIncome) TransactionTypeUi.INCOME else TransactionTypeUi.EXPENSE
        )
    }

    companion object {
        private const val FIRST_TWO_LETTERS = 2
    }
}