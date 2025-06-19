package com.yanschool.finapp.presentation.screen.today_expenses

import com.yanschool.finapp.domain.common_models.TransactionShort
import com.yanschool.finapp.presentation.model.TransactionShortUi
import com.yanschool.finapp.presentation.screen.my_expense_categories.TransactionCategoryUiMapper
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