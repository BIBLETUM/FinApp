package com.yanschool.common_viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanschool.common_mapper.TransactionDetailUiMapper
import com.yanschool.common_models.TransactionDetailUi
import com.yanschool.components.core.date_picker.DateType
import com.yanschool.domain.common_models.TransactionDetail
import com.yanschool.utils.constants.ExceptionConstants.UNEXPECTED_ERROR
import com.yanschool.utils.extensions.formatTimestampToDate
import com.yanschool.utils.extensions.toStringWithCurrency
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
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

abstract class BaseHistoryScreenViewModel<S>(
    private val setStartDateFn: (String) -> Unit,
    private val setEndDateFn: (String) -> Unit,
    dataFlow: Flow<Result<List<TransactionDetail>>>,
    private val mapper: TransactionDetailUiMapper,
    initialLoadingState: S,
    private val toContentState: (start: String, end: String, items: List<TransactionDetailUi>, total: String) -> S,
    private val toErrorState: (message: String) -> S,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, err ->
        _screenState.value = toErrorState(err.message ?: UNEXPECTED_ERROR)
    }

    private val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.getDefault())

    private val startDateFlow = MutableStateFlow(getInitialTimePeriod().first)
    private val endDateFlow = MutableStateFlow(getInitialTimePeriod().second)

    private val _screenState = MutableStateFlow(initialLoadingState)
    val screenState: StateFlow<S> = _screenState.asStateFlow()

    init {
        combine(
            startDateFlow.onEach {
                _screenState.value = initialLoadingState
                setStartDateFn(it)
            },
            endDateFlow.onEach {
                _screenState.value = initialLoadingState
                setEndDateFn(it)
            },
            dataFlow
        ) { startDate, endDate, result ->
            val formattedStartDate = parseDate(startDate)
            val formattedEndDate = parseDate(endDate)

            result.fold(
                onFailure = { err ->
                    _screenState.value = toErrorState(err.message ?: UNEXPECTED_ERROR)
                },
                onSuccess = { transactions ->
                    val mappedTransactions = transactions.map {
                        mapper.mapDomainToUi(it)
                    }

                    val total = transactions
                        .sumOf { BigDecimal(it.amount) }
                        .toStringWithCurrency()

                    _screenState.value = toContentState(
                        formattedStartDate,
                        formattedEndDate,
                        mappedTransactions,
                        total
                    )
                }
            )
        }
            .launchIn(viewModelScope.plus(exceptionHandler))
    }

    fun selectDate(timestamp: Long?, type: DateType) {
        timestamp?.formatTimestampToDate()
            ?.let { formatted ->
                when (type) {
                    DateType.START -> startDateFlow.value = formatted
                    DateType.END -> endDateFlow.value = formatted
                }
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

