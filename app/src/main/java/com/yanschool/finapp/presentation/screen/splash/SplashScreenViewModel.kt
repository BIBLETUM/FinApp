package com.yanschool.finapp.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanschool.core.constants.ExceptionConstants.UNEXPECTED_ERROR
import com.yanschool.finapp.domain.splash.IGetIsReadyToProceedFromSplashScreenFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getIsReadyToProceedFromSplashScreenFlow: IGetIsReadyToProceedFromSplashScreenFlowUseCase
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _screenState.value =
            SplashScreenState.Error(throwable.message ?: UNEXPECTED_ERROR)
    }

    private val _screenState: MutableStateFlow<SplashScreenState> =
        MutableStateFlow(SplashScreenState.Loading)
    val screenState: StateFlow<SplashScreenState> = _screenState.asStateFlow()

    init {
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
}