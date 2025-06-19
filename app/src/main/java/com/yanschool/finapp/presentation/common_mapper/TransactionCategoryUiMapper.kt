package com.yanschool.finapp.presentation.common_mapper

import com.yanschool.finapp.domain.common_models.TransactionCategory
import com.yanschool.finapp.presentation.model.TransactionCategoryUi
import com.yanschool.finapp.presentation.model.TransactionTypeUi
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