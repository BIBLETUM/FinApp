package com.yanschool.finapp.presentation.screen.today_expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanschool.core.extensions.toStringWithCurrency
import com.yanschool.finapp.domain.expenses.IGetTodayExpensesFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class TodayExpensesViewModel @Inject constructor(
    private val getTodayExpensesFlowUseCase: IGetTodayExpensesFlowUseCase,
    private val mapper: TransactionShortUiMapper,
) : ViewModel() {

    private val _screenState =
        MutableStateFlow<TodayExpensesScreenState>(TodayExpensesScreenState.Loading)
    val screenSate: StateFlow<TodayExpensesScreenState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            getTodayExpensesFlowUseCase.invoke()
                .collect { result ->
                    result.onFailure { error ->
                        _screenState.value =
                            TodayExpensesScreenState.Error(error.message ?: "Произошла ошибка")
                    }
                    result.onSuccess { data ->
                        val totalAmount = data
                            .sumOf { BigDecimal(it.amount) }
                            .toStringWithCurrency()

                        val uiItems = data
                            .map {
                                mapper.mapDomainToUi(it)
                                    .copy(amount = BigDecimal(it.amount).toStringWithCurrency())
                            }
                        _screenState.update {
                            TodayExpensesScreenState.Content(
                                transactionShortUis = uiItems,
                                total = totalAmount,
                            )
                        }
                    }
                }
        }
    }
}