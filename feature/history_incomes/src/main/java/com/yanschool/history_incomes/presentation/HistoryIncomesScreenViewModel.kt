package com.yanschool.history_incomes.presentation

import com.yanschool.common_mapper.TransactionDetailUiMapper
import com.yanschool.common_viewmodels.BaseHistoryScreenViewModel
import com.yanschool.domain.common_usecase.IGetCurrentAccountFlowUseCase
import com.yanschool.history_incomes.domain.ITransactionsIncomeHistoryInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryIncomesScreenViewModel @Inject constructor(
    private val interactor: ITransactionsIncomeHistoryInteractor,
    currentAccountFlow: IGetCurrentAccountFlowUseCase,
    mapper: TransactionDetailUiMapper,
) : BaseHistoryScreenViewModel<HistoryIncomesScreenState>(
    setStartDateFn = interactor::setStartDate,
    setEndDateFn = interactor::setEndDate,
    dataFlow = interactor.getFlow(),
    currentAccountFlow = currentAccountFlow.invoke(),
    mapper = mapper,
    initialLoadingState = HistoryIncomesScreenState.Loading,
    toContentState = { start, end, items, total ->
        HistoryIncomesScreenState.Content(
            startDate = start,
            endDate = end,
            transactions = items,
            total = total,
        )
    },
    toErrorState = { msg ->
        HistoryIncomesScreenState.Error(msg)
    }
)
