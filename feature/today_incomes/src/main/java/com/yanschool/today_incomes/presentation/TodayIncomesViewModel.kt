package com.yanschool.today_incomes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanschool.common_mapper.TransactionShortUiMapper
import com.yanschool.domain.common_models.TransactionShort
import com.yanschool.domain.common_usecase.IGetCurrentAccountFlowUseCase
import com.yanschool.today_incomes.domain.IGetTodayIncomesFlowUseCase
import com.yanschool.utils.constants.ExceptionConstants.UNEXPECTED_ERROR
import com.yanschool.utils.extensions.getCurrencySymbol
import com.yanschool.utils.extensions.toStringWithCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class TodayIncomesViewModel @Inject constructor(
    getTodayIncomesFlow: IGetTodayIncomesFlowUseCase,
    currentAccountFlow: IGetCurrentAccountFlowUseCase,
    private val mapper: TransactionShortUiMapper,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _screenState.value =
            TodayIncomesScreenState.Error(throwable.message ?: UNEXPECTED_ERROR)
    }

    private val _screenState: MutableStateFlow<TodayIncomesScreenState> = MutableStateFlow(
        TodayIncomesScreenState.Loading
    )
    val screenSate: StateFlow<TodayIncomesScreenState> = _screenState.asStateFlow()

    init {
        combine(
            getTodayIncomesFlow.invoke(),
            currentAccountFlow.invoke(),
        ) { result, account ->
            result.fold(
                onSuccess = { data ->
                    updateScreenOnSuccess(data, account.currency)
                },
                onFailure = { error ->
                    _screenState.value =
                        TodayIncomesScreenState.Error(error.message ?: UNEXPECTED_ERROR)
                }
            )
        }.launchIn(viewModelScope.plus(exceptionHandler))
    }

    private fun updateScreenOnSuccess(data: List<TransactionShort>, currentCurrency: String) {
        val currencySymbol = currentCurrency.getCurrencySymbol()

        val totalAmount = data
            .sumOf { BigDecimal(it.amount) }
            .toStringWithCurrency(currencySymbol)

        val uiItems = data
            .map {
                mapper.mapDomainToUi(it)
                    .copy(
                        amount = BigDecimal(it.amount).toStringWithCurrency(
                            currencySymbol
                        )
                    )
            }
        _screenState.update {
            TodayIncomesScreenState.Content(
                transactionShortUis = uiItems,
                total = totalAmount,
            )
        }
    }
}
