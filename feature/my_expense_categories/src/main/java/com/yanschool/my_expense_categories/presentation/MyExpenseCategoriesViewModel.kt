package com.yanschool.my_expense_categories.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanschool.common_mapper.TransactionCategoryUiMapper
import com.yanschool.common_models.TransactionCategoryUi
import com.yanschool.domain.common_models.TransactionCategory
import com.yanschool.my_expense_categories.domain.IGetTransactionCategoriesFlowUseCase
import com.yanschool.my_expense_categories.domain.ISetSearchQueryUseCase
import com.yanschool.utils.constants.ExceptionConstants.UNEXPECTED_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MyExpenseCategoriesViewModel @Inject constructor(
    private val getTransactionCategoriesFlowUseCase: IGetTransactionCategoriesFlowUseCase,
    private val setQueryUseCase: ISetSearchQueryUseCase,
    private val mapper: TransactionCategoryUiMapper,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _screenState.value =
            MyExpenseCategoriesScreenState.Error(throwable.message ?: UNEXPECTED_ERROR)
    }

    private val _queryFlow = MutableStateFlow("")

    private val _screenState = MutableStateFlow<MyExpenseCategoriesScreenState>(
        MyExpenseCategoriesScreenState.Loading
    )
    val screenState: StateFlow<MyExpenseCategoriesScreenState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch(exceptionHandler) {
            combine(
                getTransactionCategoriesFlowUseCase.invoke()
                    .map { result ->
                        mapResultDomainToUi(result)
                    },
                _queryFlow
                    .onEach {
                        setQueryUseCase(it)
                    },
            ) { result, query ->
                foldResult(result, query)
            }.collect { newState ->
                _screenState.update { newState }
            }
        }
    }

    fun setQuery(query: String) {
        _queryFlow.value = query
    }

    private fun mapResultDomainToUi(result: Result<List<TransactionCategory>>): Result<List<TransactionCategoryUi>> {
        return result.map { transactionsDomain ->
            transactionsDomain.map {
                mapper.mapDomainToUi(it)
            }
        }
    }

    private fun foldResult(
        result: Result<List<TransactionCategoryUi>>,
        query: String
    ): MyExpenseCategoriesScreenState {
        return result.fold(
            onSuccess = { data ->
                MyExpenseCategoriesScreenState.Content(
                    searchQuery = query,
                    expenseCategories = data
                )
            },
            onFailure = { error ->
                MyExpenseCategoriesScreenState.Error(
                    msg = error.message ?: UNEXPECTED_ERROR
                )
            }
        )
    }
}
