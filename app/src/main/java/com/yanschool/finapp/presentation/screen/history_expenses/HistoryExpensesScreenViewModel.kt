package com.yanschool.finapp.presentation.screen.history_expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanschool.components.core.date_picker.DateType
import com.yanschool.finapp.domain.history_expense.ITransactionsExpenseHistoryInteractor
import com.yanschool.finapp.presentation.common_mapper.TransactionDetailUiMapper
import com.yanschool.utils.constants.ExceptionConstants.UNEXPECTED_ERROR
import com.yanschool.utils.extensions.formatTimestampToDate
import com.yanschool.utils.extensions.toStringWithCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.plus
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HistoryExpensesScreenViewModel @Inject constructor(
    private val interactor: ITransactionsExpenseHistoryInteractor,
    private val mapper: TransactionDetailUiMapper,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _screenState.value =
            HistoryExpensesScreenState.Error(throwable.message ?: UNEXPECTED_ERROR)
    }

    private val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.getDefault())

    private val startDateFlow = MutableStateFlow(getInitialTimePeriod().first)
    private val endDateFlow = MutableStateFlow(getInitialTimePeriod().second)

    private val _screenState =
        MutableStateFlow<HistoryExpensesScreenState>(HistoryExpensesScreenState.Loading)
    val screenState: StateFlow<HistoryExpensesScreenState> = _screenState.asStateFlow()

    init {
        combine(
            startDateFlow.onEach {
                _screenState.value = HistoryExpensesScreenState.Loading
                interactor.setStartDate(it)
            },
            endDateFlow.onEach {
                _screenState.value = HistoryExpensesScreenState.Loading
                interactor.setEndDate(it)
            },
            interactor.getFlow(),
        ) { startDate, endDate, result ->
            val formattedStartDate = parseDate(startDate)
            val formattedEndDate = parseDate(endDate)

            result.onFailure { error ->
                _screenState.value =
                    HistoryExpensesScreenState.Error(error.message ?: UNEXPECTED_ERROR)
            }

            result.onSuccess { transactions ->
                val mappedTransactions = transactions.map {
                    mapper.mapDomainToUi(it)
                }

                val total = transactions
                    .sumOf { BigDecimal(it.amount) }
                    .toStringWithCurrency()

                _screenState.value = HistoryExpensesScreenState.Content(
                    startDate = formattedStartDate,
                    endDate = formattedEndDate,
                    transactions = mappedTransactions,
                    total = total,
                )
            }
        }.launchIn(viewModelScope.plus(exceptionHandler))
    }

    fun selectDate(date: Long?, type: DateType) {
        when (type) {
            DateType.START -> setStartDate(date)
            DateType.END -> setEndDate(date)
        }
    }

    private fun setStartDate(startDate: Long?) {
        startDate?.let {
            startDateFlow.value = it.formatTimestampToDate()
        }
    }

    private fun setEndDate(endDate: Long?) {
        endDate?.let {
            endDateFlow.value = it.formatTimestampToDate()
        }
    }

    private fun parseDate(date: String): String {
        return LocalDate.parse(date).format(formatter)
    }

    private fun getInitialTimePeriod(): Pair<String, String> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val today: LocalDate = LocalDate.now()
        val startOfMonth: LocalDate = today.withDayOfMonth(1)

        val startDate = startOfMonth.format(formatter)
        val endDate = today.format(formatter)
        return startDate to endDate
    }

}