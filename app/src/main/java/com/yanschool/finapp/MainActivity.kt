package com.yanschool.finapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yanschool.finapp.screen.TodayIncomesScreen
import com.yanschool.ui.theme.FinAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            FinAppTheme {
                systemUiController.setStatusBarColor(MaterialTheme.colorScheme.primary)
                systemUiController.setNavigationBarColor(MaterialTheme.colorScheme.surface)
                Surface {
                    TodayIncomesScreen(PaddingValues())
                }
            }
        }
    }
}