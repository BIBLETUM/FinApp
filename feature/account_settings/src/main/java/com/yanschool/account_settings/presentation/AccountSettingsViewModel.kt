package com.yanschool.account_settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanschool.account_settings.domain.IUpdateAccountInfoUseCase
import com.yanschool.domain.common_models.AccountInfo
import com.yanschool.domain.common_usecase.IGetCurrentAccountFlowUseCase
import com.yanschool.utils.constants.ExceptionConstants.UNEXPECTED_ERROR
import com.yanschool.utils.extensions.toNormalizedDecimalString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountSettingsViewModel @Inject constructor(
    private val currentAccountFlow: IGetCurrentAccountFlowUseCase,
    private val updateAccountInfoUseCase: IUpdateAccountInfoUseCase,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _screenState.value =
            AccountSettingsScreenState.Error(throwable.message ?: UNEXPECTED_ERROR)
    }

    private var currentAccountId: Int? = null

    private val _screenState =
        MutableStateFlow<AccountSettingsScreenState>(AccountSettingsScreenState.Loading)
    val screenState: StateFlow<AccountSettingsScreenState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch(exceptionHandler) {
            currentAccountFlow.invoke()
                .map { currentAccount ->
                    currentAccountId = currentAccount.id
                    AccountSettingsScreenState.Content(
                        name = currentAccount.name,
                        balance = currentAccount.balance,
                        currency = Currency.valueOf(currentAccount.currency),
                        isBalanceError = !currentAccount.balance.any { it.isDigit() },
                        isNameError = currentAccount.name.isBlank(),
                    )
                }
                .collect { newState ->
                    _screenState.update { newState }
                }
        }
    }

    fun onAction(action: AccountSettingsScreenActions) {
        when (action) {
            is AccountSettingsScreenActions.SetNewName -> setNewName(action.name)
            is AccountSettingsScreenActions.SetNewCurrency -> setNewCurrency(action.currency)
            is AccountSettingsScreenActions.SetNewBalance -> setNewBalance(action.balance)
            is AccountSettingsScreenActions.SaveChanges -> saveChanges()
        }
    }

    private fun setNewName(name: String) {
        val currentState = (_screenState.value as? AccountSettingsScreenState.Content) ?: return
        _screenState.update {
            currentState.copy(
                name = name,
                isNameError = name.isBlank()
            )
        }
    }

    private fun setNewCurrency(currency: Currency) {
        val currentState = (_screenState.value as? AccountSettingsScreenState.Content) ?: return
        _screenState.update {
            currentState.copy(currency = currency)
        }
    }

    private fun setNewBalance(balance: String) {
        val currentState = (_screenState.value as? AccountSettingsScreenState.Content) ?: return

        val parts = balance.split('.', limit = 2)
        val integerPart = parts.getOrNull(0) ?: ""

        val isIntegerPartTooLarge = integerPart.length > MAX_BALANCE_INTEGER_PART_LENGTH
        val hasAnyDigit = balance.any { it.isDigit() }

        _screenState.update {
            currentState.copy(
                balance = balance,
                isBalanceError = !hasAnyDigit || isIntegerPartTooLarge
            )
        }
    }

    private fun saveChanges() {
        val content = (_screenState.value as? AccountSettingsScreenState.Content) ?: return
        val accountInfo = content.run {
            AccountInfo(
                id = currentAccountId ?: return,
                name = name,
                balance = balance.toNormalizedDecimalString(),
                currency = currency.name,
            )
        }

        viewModelScope.launch(exceptionHandler) {
            updateAccountInfoUseCase.invoke(accountInfo)
                .onStart {
                    _screenState.value = AccountSettingsScreenState.Loading
                }
                .catch { throwable ->
                    _screenState.value = AccountSettingsScreenState.Error(
                        throwable.message ?: UNEXPECTED_ERROR
                    )
                }
                .onEach { result ->
                    result.fold(
                        onSuccess = {
                            _screenState.value = AccountSettingsScreenState.ChangesSaved
                        },
                        onFailure = { error ->
                            _screenState.value = AccountSettingsScreenState.Error(
                                error.message ?: UNEXPECTED_ERROR
                            )
                        }
                    )
                }
                .collect {}
        }
    }

    companion object {
        private const val MAX_BALANCE_INTEGER_PART_LENGTH = 13
    }
}
