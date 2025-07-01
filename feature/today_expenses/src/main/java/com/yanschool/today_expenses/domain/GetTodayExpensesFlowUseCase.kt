package com.yanschool.today_expenses.domain

import com.yanschool.domain.common_models.TransactionShort
import com.yanschool.domain.common_repository.TransactionsRepository
import com.yanschool.domain.common_usecase.IGetCurrentAccountFlowUseCase
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
    private val getAccountIdFlowUseCase: IGetCurrentAccountFlowUseCase,
) : IGetTodayExpensesFlowUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(): Flow<Result<List<TransactionShort>>> {
        val todayString: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(Calendar.getInstance().time)

        return getAccountIdFlowUseCase.invoke()
            .filterNotNull()
            .flatMapLatest { account ->
                transactionsRepository.getTransactionsShort(
                    accountId = account.id,
                    startDate = todayString,
                    endDate = todayString
                ).map { result ->
                    filterResultByExpense(result)
                }
            }
    }

    private fun filterResultByExpense(result: Result<List<TransactionShort>>): Result<List<TransactionShort>> {
        return result.map { list ->
            list.filter { !it.category.isIncome }
        }
    }
}

/**
 * Интерфейс use case для получения потока кратких расходных транзакций за сегодняшний день.
 */
interface IGetTodayExpensesFlowUseCase {

    /**
     * Возвращает поток с результатом загрузки кратких расходных транзакций за текущий день.
     *
     * @return [Flow] с [Result], содержащим список транзакций или ошибку
     */
    operator fun invoke(): Flow<Result<List<TransactionShort>>>
}
