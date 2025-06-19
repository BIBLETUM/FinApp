package com.yanschool.finapp.presentation.screen.today_incomes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanschool.core.extensions.toStringWithCurrency
import com.yanschool.finapp.domain.today_incomes.IGetTodayIncomesFlowUseCase
import com.yanschool.finapp.presentation.common_mapper.TransactionShortUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class TodayIncomesViewModel @Inject constructor(
    private val getTodayIncomesFlow: IGetTodayIncomesFlowUseCase,
    private val mapper: TransactionShortUiMapper,
) : ViewModel() {

    private val _screenState: MutableStateFlow<TodayIncomesScreenState> = MutableStateFlow(
        TodayIncomesScreenState.Loading
    )
    val screenSate: StateFlow<TodayIncomesScreenState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            getTodayIncomesFlow.invoke()
                .collect { result ->
                    result.onFailure { error ->
                        _screenState.value =
                            TodayIncomesScreenState.Error(error.message ?: "Произошла ошибка")
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
                            TodayIncomesScreenState.Content(
                                transactionShortUis = uiItems,
                                total = totalAmount,
                            )
                        }
                    }
                }
        }
    }

}