package com.yanschool.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanschool.splash.domain.IGetIsReadyToProceedFromSplashScreenFlowInteractor
import com.yanschool.utils.constants.ExceptionConstants.UNEXPECTED_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getIsReadyToProceedFromSplashScreenFlow: IGetIsReadyToProceedFromSplashScreenFlowInteractor
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is HttpException -> {
                if (throwable.code() == UNAUTHORIZED) {
                    _screenState.value = SplashScreenState.Error("Missing API token")
                } else {
                    _screenState.value = SplashScreenState.Error(throwable.message())
                }
            }

            else -> {
                _screenState.value = SplashScreenState.Error(throwable.message ?: UNEXPECTED_ERROR)
            }
        }
    }

    private val _screenState: MutableStateFlow<SplashScreenState> =
        MutableStateFlow(SplashScreenState.Loading)
    val screenState: StateFlow<SplashScreenState> = _screenState.asStateFlow()

    init {
        initAccountId()

        viewModelScope.launch(exceptionHandler) {
            getIsReadyToProceedFromSplashScreenFlow.invoke()
                .collect { isReadyToProceed ->
                    when (isReadyToProceed) {
                        true -> _screenState.value = SplashScreenState.ReadyToProceed
                        false -> _screenState.value = SplashScreenState.Loading
                    }
                }
        }
    }

    private fun initAccountId() {
        viewModelScope.launch(exceptionHandler) {
            getIsReadyToProceedFromSplashScreenFlow.loadAccountId()
        }
    }

    private companion object {
        const val UNAUTHORIZED = 401
    }
}
