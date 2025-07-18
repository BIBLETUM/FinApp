package com.yanschool.splash.presentation

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SplashScreenState {

    @Immutable
    data object Loading : SplashScreenState

    @Immutable
    data object ReadyToProceed : SplashScreenState

    @Immutable
    data class Error(val msg: String) : SplashScreenState
}
