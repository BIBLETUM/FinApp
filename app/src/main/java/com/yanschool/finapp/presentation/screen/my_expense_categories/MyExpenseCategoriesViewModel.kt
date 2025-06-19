package com.yanschool.finapp.presentation.screen.my_expense_categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanschool.core.constants.ExceptionConstants.UNEXPECTED_ERROR
import com.yanschool.finapp.domain.transaction_categories.IGetTransactionCategoriesFlowUseCase
import com.yanschool.finapp.presentation.common_mapper.TransactionCategoryUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyExpenseCategoriesViewModel @Inject constructor(
    private val getTransactionCategoriesFlowUseCase: IGetTransactionCategoriesFlowUseCase,
    private val mapper: TransactionCategoryUiMapper,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _screenState.value =
            MyExpenseCategoriesScreenState.Error(throwable.message ?: UNEXPECTED_ERROR)
    }

    private val _screenState = MutableStateFlow<MyExpenseCategoriesScreenState>(
        MyExpenseCategoriesScreenState.Loading
    )
    val screenState: StateFlow<MyExpenseCategoriesScreenState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch(exceptionHandler) {
            getTransactionCategoriesFlowUseCase.invoke()
                .map { result ->
                    result.map { transactionsDomain ->
                        transactionsDomain.map {
                            mapper.mapDomainToUi(it)
                        }
                    }
                }
                .collect { result ->
                    result.onSuccess { data ->
                        _screenState.update {
                            MyExpenseCategoriesScreenState.Content(
                                searchQuery = "", expenseCategories = data
                            )
                        }
                    }
                    result.onFailure { error ->
                        _screenState.value = MyExpenseCategoriesScreenState.Error(
                            msg = error.message ?: UNEXPECTED_ERROR
                        )
                    }
                }
        }
    }

}