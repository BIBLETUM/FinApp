package com.yanschool.finapp.data.transaction_categories

import com.yanschool.finapp.domain.transaction_categories.TransactionCategory
import javax.inject.Inject

class TransactionCategoryDtoMapper @Inject constructor() {
    fun mapDtoToDomain(dto: TransactionCategoryDto): TransactionCategory {
        return TransactionCategory(
            id = dto.id,
            name = dto.name,
            emoji = dto.emoji,
            isIncome = dto.isIncome,
        )
    }
}