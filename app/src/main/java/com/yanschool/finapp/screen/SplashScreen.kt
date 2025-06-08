package com.yanschool.finapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.yanschool.finapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreenRoot(
    continueToNextScreen: () -> Unit = {},
) {
    SplashScreenContent(
        continueToNextScreen = continueToNextScreen
    )
}

@Composable
private fun SplashScreenContent(
    continueToNextScreen: () -> Unit = {},
) {
    LaunchedEffect(Unit) {
        delay(1000)
        continueToNextScreen()
    }

    Box(
        modifier = Modifier
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