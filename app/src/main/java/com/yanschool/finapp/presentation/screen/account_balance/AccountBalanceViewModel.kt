package com.yanschool.finapp.presentation.screen.account_balance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanschool.finapp.domain.account_info.IGetAccountInfoFlowUseCase
import com.yanschool.utils.constants.ExceptionConstants.UNEXPECTED_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountBalanceViewModel @Inject constructor(
    private val getAccountInfoFlow: IGetAccountInfoFlowUseCase,
    private val mapper: AccountInfoMapper,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _screenState.value =
            AccountBalanceScreenState.Error(throwable.message ?: UNEXPECTED_ERROR)
    }

    private val _screenState =
        MutableStateFlow<AccountBalanceScreenState>(AccountBalanceScreenState.Loading)
    val screenSate: StateFlow<AccountBalanceScreenState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch(exceptionHandler) {
            getAccountInfoFlow.invoke()
                .map { result ->
                    result.map { accountInfoDomain ->
                        mapper.mapDomainToUi(accountInfoDomain)
                    }
                }
                .collect { result ->
                    result.onFailure { error ->
                        _screenState.value =
                            AccountBalanceScreenState.Error(error.message ?: UNEXPECTED_ERROR)
                    }
                    result.onSuccess { accountInfo ->
                        _screenState.value = AccountBalanceScreenState.Content(accountInfo)
                    }
                }
        }
    }

}