package com.yanschool.finapp.presentation.screen.my_expense_categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanschool.finapp.domain.transaction_categories.IGetTransactionCategoriesFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyExpenseCategoriesViewModel @Inject constructor(
    private val getTransactionCategoriesFlowUseCase: IGetTransactionCategoriesFlowUseCase,
    private val mapper: TransactionCategoryUiMapper,
) : ViewModel() {

    private val _screenState = MutableStateFlow<MyExpenseCategoriesScreenState>(
        MyExpenseCategoriesScreenState.Loading
    )
    val screenState: StateFlow<MyExpenseCategoriesScreenState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            getTransactionCategoriesFlowUseCase.invoke()
                .collect { result ->
                    result.onSuccess { data ->
                        val uiItems = data.map { mapper.mapDomainToUi(it) }
                        _screenState.update {
                            MyExpenseCategoriesScreenState.Content(
                                searchQuery = "", expenseCategories = uiItems
                            )
                        }
                    }.onFailure { error ->
                        _screenState.value = MyExpenseCategoriesScreenState.Error(
                            msg = error.message ?: "Произошла ошибка"
                        )
                    }
                }
        }
    }

}