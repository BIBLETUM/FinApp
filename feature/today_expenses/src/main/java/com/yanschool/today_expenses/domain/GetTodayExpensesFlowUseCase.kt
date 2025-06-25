package com.yanschool.today_expenses.domain

import com.yanschool.domain.common_models.TransactionShort
import com.yanschool.domain.common_repository.TransactionsRepository
import com.yanschool.domain.common_usecase.IGetAccountIdFlowUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetTodayExpensesFlowUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
    private val getAccountIdFlowUseCase: IGetAccountIdFlowUseCase,
) : IGetTodayExpensesFlowUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(): Flow<Result<List<TransactionShort>>> {
        val todayString: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(Calendar.getInstance().time)

        return getAccountIdFlowUseCase.invoke()
            .filterNotNull()
            .flatMapLatest { accountId ->
                transactionsRepository.getTransactionsShort(
                    accountId = accountId,
                    startDate = todayString,
                    endDate = todayString
                ).map { result ->
                    result.map { list ->
                        list.filter { !it.category.isIncome }
                    }
                }
            }

    }
}

interface IGetTodayExpensesFlowUseCase {
    operator fun invoke(): Flow<Result<List<TransactionShort>>>
}