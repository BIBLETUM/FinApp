package com.yanschool.finapp.presentation.common_mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.yanschool.finapp.domain.common_models.TransactionDetail
import com.yanschool.finapp.presentation.model.TransactionDetailUi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
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
            amount = domain.amount,
            comment = domain.comment,
            category = transactionCategoryUiMapper.mapDomainToUi(domain.category),
            time = formattedTime,
        )
    }
}