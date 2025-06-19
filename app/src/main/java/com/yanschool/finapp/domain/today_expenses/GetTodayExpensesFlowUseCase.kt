package com.yanschool.finapp.domain.today_expenses

import com.yanschool.finapp.domain.common_models.TransactionShort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetTodayExpensesFlowUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
) : IGetTodayExpensesFlowUseCase {

    override fun invoke(): Flow<Result<List<TransactionShort>>> {
        val todayString: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(Calendar.getInstance().time)

        return transactionsRepository.getTransactions(
            startDate = todayString,
            endDate = todayString
        ).map { result ->
            result.map { list ->
                list.filter { !it.category.isIncome }
            }
        }
    }
}

interface IGetTodayExpensesFlowUseCase {
    operator fun invoke(): Flow<Result<List<TransactionShort>>>
}