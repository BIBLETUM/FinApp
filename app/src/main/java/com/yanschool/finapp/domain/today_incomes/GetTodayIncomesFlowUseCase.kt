package com.yanschool.finapp.domain.today_incomes

import com.yanschool.domain.common_models.TransactionShort
import com.yanschool.finapp.domain.today_expenses.TransactionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetTodayIncomesFlowUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
) : IGetTodayIncomesFlowUseCase {

    override fun invoke(): Flow<Result<List<TransactionShort>>> {
        val todayString: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(Calendar.getInstance().time)

        return transactionsRepository.getTransactionsShort(
            startDate = todayString,
            endDate = todayString,
        ).map { result ->
            result.map { list ->
                list.filter { it.category.isIncome }
            }
        }
    }
}

interface IGetTodayIncomesFlowUseCase {
    operator fun invoke(): Flow<Result<List<TransactionShort>>>
}