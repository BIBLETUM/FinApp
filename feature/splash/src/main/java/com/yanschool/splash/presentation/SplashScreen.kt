package com.yanschool.splash.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.yanschool.components.core.ErrorScreen
import com.yanschool.finapp.splash.R

@Composable
fun SplashScreenRoot(
    continueToNextScreen: () -> Unit = {},
    viewModel: SplashScreenViewModel = hiltViewModel()
) {
    val screenState = viewModel.screenState.collectAsStateWithLifecycle()

    SplashScreen(
        continueToNextScreen = continueToNextScreen,
        screenState = screenState
    )
}

@Composable
private fun SplashScreen(
    continueToNextScreen: () -> Unit,
    screenState: State<SplashScreenState>
) {
    when (val currentScreenState = screenState.value) {
        is SplashScreenState.Error -> {
            Log.d("SplashScreen", currentScreenState.msg)
            ErrorScreen(
                modifier = Modifier.fillMaxSize(),
                message = currentScreenState.msg
            )
        }

        is SplashScreenState.Loading -> {
            SplashScreenContent()
        }

        is SplashScreenState.ReadyToProceed -> {
            continueToNextScreen()
        }
    }
}

@Composable
private fun SplashScreenContent() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.splash_screen_animation)
        )

        LottieAnimation(
            modifier = Modifier.size(400.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    }
}
