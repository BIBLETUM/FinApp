package com.yanschool.history_expenses.presentation

import com.yanschool.common_mapper.TransactionDetailUiMapper
import com.yanschool.common_viewmodels.BaseHistoryScreenViewModel
import com.yanschool.history_expenses.domain.ITransactionsExpenseHistoryInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryExpensesScreenViewModel @Inject constructor(
    private val interactor: ITransactionsExpenseHistoryInteractor,
    mapper: TransactionDetailUiMapper,
) : BaseHistoryScreenViewModel<HistoryExpensesScreenState>(
    setStartDateFn = interactor::setStartDate,
    setEndDateFn = interactor::setEndDate,
    dataFlow = interactor.getFlow(),
    mapper = mapper,
    initialLoadingState = HistoryExpensesScreenState.Loading,
    toContentState = { start, end, items, total ->
        HistoryExpensesScreenState.Content(
            startDate = start,
            endDate = end,
            transactions = items,
            total = total,
        )
    },
    toErrorState = { msg ->
        HistoryExpensesScreenState.Error(msg)
    }
)