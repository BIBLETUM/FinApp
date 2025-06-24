package com.yanschool.finapp.presentation.common_mapper

import com.yanschool.domain.common_models.TransactionDetail
import com.yanschool.finapp.presentation.model.TransactionDetailUi
import com.yanschool.utils.extensions.toStringWithCurrency
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class TransactionDetailUiMapper @Inject constructor(
    private val transactionCategoryUiMapper: TransactionCategoryUiMapper,
) {

    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        .withZone(ZoneId.systemDefault())

    fun mapDomainToUi(domain: TransactionDetail): TransactionDetailUi {

        val formattedTime = Instant
            .parse(domain.dateTime)
            .atZone(ZoneId.systemDefault())
            .format(timeFormatter)

        return TransactionDetailUi(
            id = domain.id,
            amount = BigDecimal(domain.amount).toStringWithCurrency(),
            comment = domain.comment,
            category = transactionCategoryUiMapper.mapDomainToUi(domain.category),
            time = formattedTime,
        )
    }
}